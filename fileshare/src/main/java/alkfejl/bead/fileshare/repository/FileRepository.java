package alkfejl.bead.fileshare.repository;

import alkfejl.bead.fileshare.model.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
    Optional<File> findByFullPath(String path);
    Iterable<File> findByPath(String path);
    File findById(Long id);
}
