package alkfejl.bead.fileshare.controller;

import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    @GetMapping("/upload")
    public String upload(Model model) {
        model.addAttribute("upload", new File());
        return "upload";
    }

    @PostMapping("/upload")
    public String upload(@ModelAttribute File file) {
        fileService.uploadFile(file);
        return "uploadSuccess";
    }
    @GetMapping("/uploadSuccess")
    public String uploadSuccess(Model model) {
        model.addAttribute("uploadSuccess", new File());
        return "upload";
    }

}
