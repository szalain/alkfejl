package alkfejl.bead.fileshare.controller;


import alkfejl.bead.fileshare.model.Comment;
import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.CommentService;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.annotations.Role;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

import static alkfejl.bead.fileshare.model.User.Role.ADMIN;
import static alkfejl.bead.fileshare.model.User.Role.MOD;
import static alkfejl.bead.fileshare.model.User.Role.USER;

@RestController
public class CommentApiController {

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
    @RequestMapping(value="/api/showFile/**/comment", method = RequestMethod.POST)
    public ResponseEntity report(HttpServletRequest request, @RequestParam("comment") String commentText) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/api/showFile", "");
        restOfTheUrl = restOfTheUrl.substring(0, restOfTheUrl.length()-8);
        try {
            commentService.createComment(commentText, restOfTheUrl);
            return ResponseEntity.status(HttpStatus.OK).body("Comment created!");
        } catch (UserNotValidException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not valid or is banned!");
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    //@Role({ADMIN, MOD, USER})
    @GetMapping("/api/showFile/**/comments")
    public ResponseEntity listCommentsByFile(HttpServletRequest request) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/api/showFile", "");
        restOfTheUrl = restOfTheUrl.substring(0, restOfTheUrl.length()-9);
        Iterable<Comment> c = null;
        try {
            c = commentService.listCommentsByFile(restOfTheUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(c);
    }

    @RequestMapping(value = "/api/showFile/**/comments/{id}", method =RequestMethod.DELETE)
    public ResponseEntity deleteCommentById(HttpServletRequest request, @PathVariable Long id) {
        String restOfTheUrl = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        restOfTheUrl = restOfTheUrl.replaceAll("/api/showFile", "");
        restOfTheUrl = restOfTheUrl.substring(0, restOfTheUrl.length()-9);
        Iterable<Comment> c = null;
        try {
            commentService.deleteCommentById(id);
        } catch (UserNotValidException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User is not valid or is banned!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(c);
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
