package alkfejl.bead.fileshare.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;



@Controller
@RequestMapping("/uploadFile")
public class UploadController {

    @Autowired
    StorageService storageService;

    List<String> files = new ArrayList<String>();

    @GetMapping("")
    public String listUploadedFiles(Model model) {
        return "uploadForm";
    }

    @PostMapping("")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("path") String path, Model model) {
        try {
            storageService.store(file, path);
            model.addAttribute("message", "You successfully uploaded " + path + file.getOriginalFilename() + "!");
            files.add(file.getOriginalFilename());
        } catch (Exception e) {
            model.addAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
        }
        return "uploadForm";
    }

/*    @GetMapping("getallfiles")
    public String getListFiles(Model model) {
        model.addAttribute("files",
                files.stream()
                        .map(fileName -> MvcUriComponentsBuilder
                                .fromMethodName(UploadController.class, "getFile", fileName).build().toString())
                        .collect(Collectors.toList()));
        model.addAttribute("totalFiles", "TotalFiles: " + files.size());
        return "listFiles";
    }
*/
@GetMapping("getallfiles")
public String getListFiles(Model model) {
    Iterable<File> f = storageService.findAll();
    model.addAttribute("files", f);
    return "listFiles";
}

    @GetMapping("files/{fullPath}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String fullPath) {
        Resource file = storageService.loadFile(storageService.findID(fullPath));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}