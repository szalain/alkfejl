package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.repository.UserRepository;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;

    public User register(User user) {
        user.setRole(USER);
        userRepository.save(user);
        return user;
    }

    public void updateRole(User user, User.Role role) {
        user.setRole(role);
        userRepository.save(user);
    }

    public void updateStatus(User user, boolean status) {
        user.setBanned(status);
        userRepository.save(user);
    }

    /*public Optional<User> findUser(String username, String email) {
        return userRepository.findByUsernameAndEmail(username, email);
    }*/

    public User login(User user) throws UserNotValidException {
        if (isValid(user)) {
            //return this.user = userRepository.findByUsername(user.getUsername()).get();
            return this.user = userRepository.findByUsernameIgnoreCase(user.getUsername()).get();
        }
        throw new UserNotValidException();
    }

    public User logout(User user) {
        if(isLoggedIn()) {System.out.println("logged in!"); return this.user = null;}
        return null;
    }

    public boolean isValid(User user) {
        //return userRepository.findByUsernameIgnoreCaseAndPassword(user.getUsername(), user.getPassword()).isPresent();
        if (!userRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent()) return false;
        User dbUser = this.getUser(user.getUsername());
        return (passwordEncoder.matches(user.getPassword(),dbUser.getPassword()));
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public boolean isFound(User user) {
        return userRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent();
    }

    public boolean isBanned(User user) {
        return userRepository.findByUsernameAndBannedTrue(user.getUsername()).isPresent();
    }

    public User getUser(String username) {
        return userRepository.findByUsernameIgnoreCase(username).get();
    }

    public boolean isDataDuplicated(User user) {
        return userRepository.findByUsernameIgnoreCase(user.getUsername()).isPresent() || userRepository.findByEmailIgnoreCase(user.getEmail()).isPresent();
    }

    public boolean isDataValid(User user) {
        return user.getUsername().matches("[a-zA-Z][a-zA-Z0-9]{2,15}")
                && user.getEmail().matches("[a-zA-Z0-9]{3,16}@[a-zA-Z0-9]{3,16}(\\.[a-zA-Z]{2,8}){1,3}")
                && user.getPassword().matches("[a-zA-Z0-9]{5,16}");
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }
}
