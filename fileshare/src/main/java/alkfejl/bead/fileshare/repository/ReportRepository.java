package alkfejl.bead.fileshare.repository;

import alkfejl.bead.fileshare.model.Report;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportRepository extends CrudRepository<Report, Long> {

    Report findById(Long id);
}
