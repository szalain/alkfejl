package alkfejl.bead.fileshare.api;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.annotations.Role;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static alkfejl.bead.fileshare.model.User.Role.ADMIN;
import static alkfejl.bead.fileshare.model.User.Role.MOD;
import static alkfejl.bead.fileshare.model.User.Role.USER;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    private final UserService userService;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity user() {
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.ok(false);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            if (userService.isBanned(user)) return ResponseEntity.status(403).build();
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        this.userService.setUser(null);
        return ResponseEntity.ok(false);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if(userService.isDataDuplicated(user)) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(userService.register(user));
    }

    public UserService getUserService() {
        return userService;
    }
}