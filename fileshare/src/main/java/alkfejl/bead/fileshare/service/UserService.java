package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.repository.UserRepository;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Optional;

import static alkfejl.bead.fileshare.model.User.Role.USER;

@Service
@SessionScope
@Data
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private User user;

    public User register(User user) {
        user.setRole(USER);
        userRepository.save(user);
        return user;
    }

    /*public Optional<User> findUser(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }*/

    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            return this.user = userRepository.findByUsername(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }

    public User logout(User user) {
        if(isLoggedIn()) {System.out.println("logged in!"); return this.user = null;}
        return null;
    }

    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public boolean isFound(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent();
    }

    public boolean isBanned(User user) {
        return userRepository.findByUsernameAndBannedTrue(user.getUsername()).isPresent();
    }

    public User getUser(String username) {
        return userRepository.findByUsername(username).get();
    }

    public boolean isDataDuplicated(User user) {
        return userRepository.findByUsername(user.getUsername()).isPresent() || userRepository.findByEmail(user.getEmail()).isPresent();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
}
