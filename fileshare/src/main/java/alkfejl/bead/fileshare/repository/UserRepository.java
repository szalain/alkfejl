package alkfejl.bead.fileshare.repository;

import alkfejl.bead.fileshare.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByUsername(String username);
    //User findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByEmailIgnoreCase(String email);
    Optional<User> findByUsernameIgnoreCaseAndPassword(String username, String password);
    Optional<User> findByUsernameAndEmail(String username, String email);
    Optional<User> findByUsernameAndBannedTrue(String username);
    Optional<User> findById(Long id);

}
