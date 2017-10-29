package hu.elte.alkfelj.dataman.dataman.Controller;

import hu.elte.alkfelj.dataman.dataman.Entity.Request.AddUserRequest;
import hu.elte.alkfelj.dataman.dataman.Entity.User;
import hu.elte.alkfelj.dataman.dataman.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserRespository userRepository;

    @Autowired
    public UserController(UserRespository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addUser(@RequestBody AddUserRequest addUserRequest) {
        User user = new User();
        user.setName(addUserRequest.getName());
        user.setEmail(addUserRequest.getEmail());
        user.setPassword(addUserRequest.getPassword());
        user.setRole(addUserRequest.getRole());
        user.setUploadCount(addUserRequest.getUploadCount());
        user.setBanned(addUserRequest.isBanned());
        userRepository.save(user);
    }
}
