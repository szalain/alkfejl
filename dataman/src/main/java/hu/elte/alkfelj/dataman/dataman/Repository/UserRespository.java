package hu.elte.alkfelj.dataman.dataman.Repository;

import hu.elte.alkfelj.dataman.dataman.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
}
