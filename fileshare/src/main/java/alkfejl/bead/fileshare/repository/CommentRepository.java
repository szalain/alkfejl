package alkfejl.bead.fileshare.repository;

import alkfejl.bead.fileshare.model.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
    Optional<Comment> findCommentById(Long id);
    Iterable<Comment> findAllByCommentedFileId(Long id);
    void deleteAllByCommentedFileId(Long id);
    void deleteCommentById(Long id);
    Comment findById(Long id);
}
