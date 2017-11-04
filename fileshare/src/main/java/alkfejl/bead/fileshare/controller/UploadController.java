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
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;


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
        boolean success=false;
        try {
            if(file.getSize()<500000) {
            storageService.store(file, path);
            success=true;
            files.add(file.getOriginalFilename());
            } else {
                throw new IllegalStateException();
            }
        } catch (IllegalStateException i) {
            return "fileTooBig";
        }
        catch (Exception e) {
            model.addAttribute("message", "FAIL to upload " + file.getOriginalFilename() + "!");
        }
        //String substring = path.substring(0, path.length() - 1);
        return "redirect:/uploadFile/getallfiles" + path + "?success=" + success;
    }

    @PostMapping("createDir")
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("location") String location, Model model) {
        try {
            storageService.store(location, name);
            model.addAttribute("message", "You successfully uploaded " + name + "!");
        } catch (Exception e) {
            model.addAttribute("message", "FAIL to create " + name + "!");
        }
        //String substring = location.substring(0, location.length() - 1);
        return "redirect:/uploadFile/getallfiles" + location;
    }

    @RequestMapping("getallfiles/**")
    public String getListFiles(HttpServletRequest request, @RequestParam(value = "success", required = false) String success, Model model) {
        String value="Some kind of error!";
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/uploadFile/getallfiles", "");
        //restOfTheUrl = "/" + restOfTheUrl.substring(1, restOfTheUrl.length());
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

    @GetMapping("files/**")
    @ResponseBody
    public ResponseEntity<Resource> getFile(HttpServletRequest request, Model model) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/uploadFile/files", "");
        Resource file = storageService.loadFile(storageService.findID(restOfTheUrl));
        restOfTheUrl = restOfTheUrl.replaceAll(".*/", "");
        ResponseEntity RE = ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + restOfTheUrl + "\"")
                .body(file);

        return RE;
    }

}