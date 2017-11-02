package alkfejl.bead.fileshare.repository;

import alkfejl.bead.fileshare.model.File;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CrudRepository<File, Long> {
}
