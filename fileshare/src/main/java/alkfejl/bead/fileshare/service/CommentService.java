package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.Comment;
import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.repository.CommentRepository;
import alkfejl.bead.fileshare.repository.FileRepository;
import alkfejl.bead.fileshare.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    public void createComment(String commentText, String fullPath) {
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setUser(userRepository.findById(0l));
        comment.setCommentedFile(fileRepository.findByFullPath(fullPath).get());
        commentRepository.save(comment);
    }

    public Iterable<Comment> listComments() {

        return commentRepository.findAll();
    }

    /*
    public Comment getComment(Long id) {

        return commentRepository.findById(id);
    }

    public void deleteReport(Report report) {
        reportRepository.delete(report);
    }
    */


}