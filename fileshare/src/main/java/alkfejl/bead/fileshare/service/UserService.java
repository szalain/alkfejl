package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public boolean isBanned(User user) {
        return userRepository.findByUsernameAndBannedTrue(user.getUsername()).isPresent();
    }
}
