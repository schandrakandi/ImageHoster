package ImageHoster.repository;

import ImageHoster.model.Image;
import ImageHoster.model.Comment;
import ch.qos.logback.core.CoreConstants;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CommentRepository {
    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    //The method receives the Image object to be persisted in the database
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public Comment updateComment(Comment newComment) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }

    //The method receives the image id to get the comments from comments table
    //Creates an instance of EntityManager
    //Starts a transaction
    //The transaction is committed if it is successful
    //The transaction is rolled back in case of unsuccessful transaction
    public List<Comment> getCommentsById(Integer image_id) {

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try {
             TypedQuery<Comment> query = em.createQuery("SELECT i from Comment i", Comment.class);
            List<Comment> resultList = query.getResultList();
            List<Comment> returnList = new ArrayList<Comment>();
            for(Comment comment : resultList){
                 if(comment.getImage().getId() == image_id){
                    returnList.add(comment);
                }
            }
            return returnList;
        } catch (NoResultException nre) {
            return null;
        }
    }
}
