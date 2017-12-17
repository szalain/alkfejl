package alkfejl.bead.fileshare.api;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.service.UserService;
import alkfejl.bead.fileshare.service.annotations.Role;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

import static alkfejl.bead.fileshare.model.User.Role.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController {

    @Autowired
    private final UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity user() {
        //System.out.println(userService.isLoggedIn());
        if (userService.isLoggedIn()) {
            return ResponseEntity.ok(userService.getUser());
        }
        return ResponseEntity.ok(false);
    }

    @Role(GUEST)
    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        try {
            //User dbUser = userService.getUser(user.getUsername());
            //System.out.println(passwordEncoder.matches(user.getPassword(),dbUser.getPassword()));
            //if (!passwordEncoder.matches(user.getPassword(),dbUser.getPassword())) return ResponseEntity.badRequest().build();
            if (userService.isBanned(user)) return ResponseEntity.status(403).build();
            return ResponseEntity.ok(userService.login(user));
        } catch (UserNotValidException e) {
            return ResponseEntity.badRequest().build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/logout")
    public ResponseEntity logout() {
        this.userService.setUser(null);
        return ResponseEntity.ok(false);
    }

    @Role(GUEST)
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        if(userService.isDataDuplicated(user)) return ResponseEntity.badRequest().build();
        if(!userService.isDataValid(user)) return ResponseEntity.badRequest().build();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.register(user));
    }

    @Role(ADMIN)
    @PostMapping("/control")
    public ResponseEntity<User> getUser(@RequestBody String username) {
        try {
            username = username.split(":")[1].replaceAll("[\"{}]","");
            User user = userService.getUser(username);
            return ResponseEntity.ok(user);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Role(ADMIN)
    @PatchMapping("/control/modify/{id}")
    public ResponseEntity<User> modifyRole(@PathVariable Long id, @RequestBody /*User.Role*/ String role) {
        try {
            //User user = userService.getUserById(id);
            role = role.split(":")[1].replaceAll("[\"{}]","");
            switch (role) {
                case "USER":
                    //userService.getUserById(id).setRole(User.Role.USER);
                    userService.updateRole(userService.getUserById(id),User.Role.USER);
                    System.out.println("USER");
                    break;
                case "MOD":
                    //userService.getUserById(id).setRole(User.Role.MOD);
                    userService.updateRole(userService.getUserById(id),User.Role.MOD);
                    System.out.println("MOD");
                    break;
                case "ADMIN":
                    //userService.getUserById(id).setRole(User.Role.ADMIN);
                    userService.updateRole(userService.getUserById(id),User.Role.ADMIN);
                    System.out.println("ADMIN");
                    break;
            }
            //return ResponseEntity.ok(true);
            //System.out.println(ResponseEntity.ok(user));
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Role(ADMIN)
    @PatchMapping("/control/ban/{id}")
    public ResponseEntity banUser(@PathVariable Long id) {
        try {
            //User user = userService.getUserById(id);
            //user.setBanned(true);
            userService.updateStatus(userService.getUserById(id),true);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Role(ADMIN)
    @PatchMapping("/control/unban/{id}")
    public ResponseEntity unbanUser(@PathVariable Long id) {
        try {
            //User user = userService.getUserById(id);
            //user.setBanned(false);
            //System.out.println(user.isBanned());
            userService.updateStatus(userService.getUserById(id),false);
            return ResponseEntity.ok(true);
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }


    public UserService getUserService() {
        return userService;
    }
}