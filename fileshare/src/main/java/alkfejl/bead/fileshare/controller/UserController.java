package alkfejl.bead.fileshare.controller;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/greet")
    public String greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {
        model.addAttribute("name",name);
        return "greeting";
    }

    @GetMapping("/login")
    public String login(Model model) {
        if (userService.isLoggedIn()) return "index";
        model.addAttribute(new User());
        return "login";
    }

    @GetMapping("/index")
    public String index(Model model, User user) {
        //User információk
        return "todo";
    }

    @GetMapping("/logout")
    public String logout(User user) {
        userService.logout(user);
        return "index";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if (userService.isValid(user) && !userService.isBanned(user)) {
            try {
                userService.login(user);
            } catch (UserNotValidException e) {
                //return ResponseEntity.badRequest().build();
            }
            return redirectToGreeting(user);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        if (userService.isLoggedIn()) return "index";
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute User user, Model model) {
        if (user.getPassword().equals("") || user.getEmail().equals("") || user.getUsername().equals("")) {
            model.addAttribute("blankData", true);
            return "register";
        }
        if (!userService.isDataDuplicated(user)) {
            userService.register(user);
            return "login";
        }
        model.addAttribute("registerFailed", true);
        return "register";
    }

    private String redirectToGreeting(@ModelAttribute User user) {
        return "redirect:/greet?name=" + user.getUsername();
    }
}