package alkfejl.bead.fileshare.controller;

import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/uploadFiles")
public class UploadController {

    @Autowired
    UploadService storageService;
    
    @RequestMapping("/**")
    public String getListFiles(HttpServletRequest request, @RequestParam(value = "success", required = false) String success, Model model) {
        String value="Some kind of error!";
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/uploadFiles", "");
        if(storageService.isDirPresent(restOfTheUrl)) {
            if ("true".equals(success)) {
                model.addAttribute("message", "You successfully uploaded the file!");
            } else if (success!=null) {
                model.addAttribute("message", "Upload FAILED!");
            }
            Iterable<File> f = storageService.findAllByPath(restOfTheUrl);
            model.addAttribute("files", f);
            model.addAttribute("location", (restOfTheUrl));
            return "listFiles";
        } else {
            value="This path doesn't exist: ";
            model.addAttribute("message", value);
            model.addAttribute("location", (restOfTheUrl));
            return "listFilesError";
        }
    }

    @PostMapping("upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("path") String path, Model model) {
        boolean success=false;
        try {
            storageService.store(file, path);
            success=true;
        } catch (Exception e) {
            model.addAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
        }
        return "redirect:/uploadFiles" + path + "?success=" + success;
    }

    @PostMapping("createDir")
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("location") String location, Model model) {
        try {
            storageService.store(location, name);
            model.addAttribute("message", "You successfully uploaded " + name + "!");
        } catch (Exception e) {
            model.addAttribute("message", "FAIL to create " + name + "!");
        }
        return "redirect:/uploadFiles" + location;
    }

    @GetMapping("files/**")
    @ResponseBody
    public ResponseEntity<Resource> getFile(HttpServletRequest request, Model model) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/uploadFiles/files", "");
        Resource file = storageService.loadFile(storageService.findID(restOfTheUrl));
        restOfTheUrl = restOfTheUrl.replaceAll(".*/", "");

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + restOfTheUrl + "\"")
                .body(file);
    }

}