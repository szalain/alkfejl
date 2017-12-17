package alkfejl.bead.fileshare.service;

import alkfejl.bead.fileshare.model.Comment;
import alkfejl.bead.fileshare.model.File;
import alkfejl.bead.fileshare.model.User;
import alkfejl.bead.fileshare.repository.CommentRepository;
import alkfejl.bead.fileshare.repository.FileRepository;
import alkfejl.bead.fileshare.repository.UserRepository;
import alkfejl.bead.fileshare.service.exceptions.UserNotValidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private UserService userService;

    public void createComment(String commentText, String fullPath) throws Exception {
        User user = userService.getUser();
        if (user==null || !userService.isValid(user) || userService.isBanned(user)) {
            throw new UserNotValidException();
        }
        if (!fileRepository.findByFullPath(fullPath).isPresent()) {
            throw new FileNotFoundException("File not found!");
        }
        Comment comment = new Comment();
        comment.setText(commentText);
        comment.setUser(user);
        comment.setCommentedFile(fileRepository.findByFullPath(fullPath).get());
        commentRepository.save(comment);
    }

    public Iterable<Comment> listComments() {

        return commentRepository.findAll();
    }

    public Iterable<Comment> listCommentsByFile(String fullPath) throws Exception {
        if(fileRepository.findByFullPath(fullPath).isPresent()) {
            Long id = fileRepository.findByFullPath(fullPath).get().getId();
            Iterable<Comment> comments = commentRepository.findAllByCommentedFileId(id);
            return comments;
        }
        throw new FileNotFoundException("No such file!");
    }

    public void deleteCommentById(Long id) throws Exception {
        User user = userService.getUser();
       if (user==null || !userService.isValid(user) || userService.isBanned(user)) {
            throw new UserNotValidException("User is not valid or is banned!");
        }
        if(!commentRepository.findCommentById(id).isPresent())
            throw new NoSuchElementException("There is no comment with that ID!");
        if(user.getRole().equals(User.Role.ADMIN) || user.getRole().equals(User.Role.MOD) || commentRepository.findById(id).getUser().getId()==user.getId()) {
            commentRepository.deleteCommentById(id);
        } else {
           throw new UserNotValidException("Comment is not created by current user!");
        }

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