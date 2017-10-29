package hu.elte.alkfelj.dataman.dataman.Repository;

import hu.elte.alkfelj.dataman.dataman.Entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
