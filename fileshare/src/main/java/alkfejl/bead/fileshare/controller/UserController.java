package alkfejl.bead.fileshare.controller;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static alkfejl.bead.fileshare.model.User.Role.USER;

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
        model.addAttribute(new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute User user, Model model) {
        if (userService.isValid(user) && !userService.isBanned(user)) {
            return redirectToGreeting(user);
        }
        model.addAttribute("loginFailed", true);
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    //TODO: Ha egyező username/email van, akkor ne legyen regisztráció
    @PostMapping("/register")
    public String register(@ModelAttribute User user) {
        user.setRole(USER);
        //user.setUploadCount(0);
        //user.setBanned(false);
        userService.register(user);
        return redirectToGreeting(user);
    }

    private String redirectToGreeting(@ModelAttribute User user) {
        return "redirect:/greet?name=" + user.getUsername();
    }
}