package hu.elte.alkfelj.dataman.dataman.Repository;

import hu.elte.alkfelj.dataman.dataman.Entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
