package alkfejl.bead.fileshare.controller;


import alkfejl.bead.fileshare.model.Comment;
import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.CommentService;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.annotations.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static alkfejl.bead.fileshare.model.User.Role.ADMIN;
import static alkfejl.bead.fileshare.model.User.Role.MOD;
import static alkfejl.bead.fileshare.model.User.Role.USER;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;

    //@Role({ADMIN, MOD, USER})
  /*  @GetMapping("/comment")
    public String report(Model model) {
        model.addAttribute(new Comment());
        return "listFiles";
    }*/

    //@Role({ADMIN, MOD, USER})
    @PostMapping("/comment")
    public String report(@RequestParam("commentText") String commentText, @RequestParam("fullPath") String fullPath, @RequestParam("location") String location) {
        commentService.createComment(commentText, fullPath);
        return "redirect:/uploadFiles"+location;
    }

    @Role({ADMIN, MOD, USER})
    @GetMapping("/commentlist")
    public String getReports(Model model) {
        Iterable<Comment> c = commentService.listComments();
        model.addAttribute("comments", c);
        return "listFiles";
    }
/*
    @Role({ADMIN, MOD})
    @GetMapping("/reportlist/ban")
    public String banUser(@RequestParam(value = "id") Long id) {
        //model.addAttribute("name", name);
        User u = userService.getUserById(id);
        u.setBanned(true);
        userService.register(u);
        System.out.println("User bannolva: " + u.getUsername());
        return "redirect:/reportlist";
    }

    @Role({ADMIN, MOD})
    @GetMapping("/reportlist/delete")
    public String deleteReport(@RequestParam(value = "id") Long id) {
        Report r = reportService.getReport(id);
        reportService.deleteReport(r);
        return "redirect:/reportlist";
    }
    */

}
