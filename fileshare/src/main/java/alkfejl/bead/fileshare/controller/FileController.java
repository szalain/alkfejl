package alkfejl.bead.fileshare.controller;

import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        if(!fileService.exists(file.getPath())){
            fileService.uploadFile(file);
            return "redirect:/uploadSuccess";
        } else {
            return "redirect:/uploadFailed";
        }
    }

    @RequestMapping("/uploadSuccess")
    public String uploadSuccess() {
        return "uploadSuccess";
    }

    @RequestMapping("/uploadFailed")
    public String uploadFailed() {
        return "uploadFailed";
    }

}