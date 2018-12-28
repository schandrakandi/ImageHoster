package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void updateComment(Comment comment) {
        commentRepository.updateComment(comment);
    }
    /*public List<Comment> getAllComments() {
        List<Comment> results = commentRepository.getAllComments();
        return results;
    }*/
    public List<Comment> getCommentsById(Integer imageId) {
        List<Comment> results = commentRepository.getCommentsById(imageId);
        return results;
    }

}
