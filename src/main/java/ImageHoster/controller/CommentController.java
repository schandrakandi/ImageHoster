package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

     //This controller method is called when the request pattern is of type '/image/{imageId}/{imageTitle}/comments' and also the incoming request is of POST type
    //The method receives the imageId, imageTitle , text , along with the Http Session
    //The method adds the new comment to the comments table
    //Set the user using Http Session
    //Call the updateComment() method in the business logic to update the comment
    //Re direct Direct to the /images/" + imageId
    //The method also receives tags parameter which is a string of all the tags separated by a comma using the annotation @RequestParam,@PathVariable
   
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComment(@PathVariable("imageId") Integer imageId, @PathVariable("imageTitle") String imageTitle, @RequestParam("comment") String text,Comment comment, HttpSession session) throws IOException {
        User user = (User) session.getAttribute("loggeduser");
        Image image = imageService.getImageById(imageId);
        comment.setUser(user);
        comment.setText(text);
        comment.setImage(image);
        comment.setCreatedDate(new Date());
        commentService.updateComment(comment);
        return "redirect:/images/" + imageId;
    }

}
