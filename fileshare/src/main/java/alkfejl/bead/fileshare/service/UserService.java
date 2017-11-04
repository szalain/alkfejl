package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void register(User user) {
        userRepository.save(user);
    }

    public boolean isValid(User user) {
        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()).isPresent();
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

    public User getUserById(Long id) {
        return userRepository.findById(id);
    }
}
