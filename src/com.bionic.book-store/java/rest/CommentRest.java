package rest;

import entities.Book;
import entities.Comment;
import entities.User;
import jsonClasses.CommentJson;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by UFO on 03.08.2014.
 */
@Path("comment/")
public class CommentRest {

    private final String RESPONSE_HEADER = "Access-Control-Allow-Origin";

    @Path("forBook/{bookId}")
    @GET
    @Produces("application/json")
    public ArrayList<CommentJson> search(@PathParam("bookId") String bookId) {
        List<Comment> comments = new ArrayList<>();
        ArrayList<CommentJson> result = new ArrayList<CommentJson>();
        try {
            comments = DaoFactory.getDaoCommentsInstance().selectByBookId(Integer.parseInt(bookId));
            if (comments == null)
                return result;
            for (Comment c : comments) {
                CommentJson comment = new CommentJson(c);
                result.add(comment);
            }
        } catch (NullPointerException e) {

        }


        // Logger.log(Logger.Type.PROCESS,"SEARCH:"+user!=null?user:"Someone"+" has found "+s);
        return result;
    }

    @Path("addComment")
    @POST
    public javax.ws.rs.core.Response addComment(@CookieParam("user") String enteredUser,
                                                @FormParam("book") String currentBook,
                                                @FormParam("comment") String description) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(enteredUser);
        Book book = DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(currentBook));

        if (!checkUser(user)) {
            Logger.log(Logger.Type.PROCESS, "Access denied : " + enteredUser);
            return javax.ws.rs.core.Response.status(403).header(RESPONSE_HEADER, "*")
                    .entity("Вибачте, ви не маєте досупу до даної операції").build();
        }

        Comment comment = new Comment();
        comment.setDate(new Timestamp(new Date().getTime()));
        comment.setBookByBookId(book);
        comment.setUserByUserId(user);
        comment.setCommDesc(description);
        DaoFactory.getDaoCommentsInstance().insert(comment);

        return javax.ws.rs.core.Response.ok().build();
    }

    private boolean checkUser(User user) {
        if (user == null) return false;
        // TODO check user group

        return true;
    }

    @Path("search/{searchString}")
    @GET
    @Produces ("application/json")
    public List <CommentJson> searchComments(@PathParam("searchString")String searchString){
        List<Comment> comments = new LinkedList<>();
        ArrayList<CommentJson> result = new ArrayList<CommentJson>();
        try {
            comments = DaoFactory.getDaoCommentsInstance().search(searchString);
            if (comments == null)
                return result;
            for (Comment c : comments) {
                CommentJson comment = new CommentJson(c);
                result.add(comment);
            }
        } catch (NullPointerException e) {

        }


        // Logger.log(Logger.Type.PROCESS,"SEARCH:"+user!=null?user:"Someone"+" has found "+s);
        return result;
    }

    @GET
    @Path("getPageCount")
    @Produces("text/plain")
    public String getPageCount(){
        return DaoFactory.getDaoCommentsInstance().count().toString();
    }

    @Path("getFromPage/{page}")
    @GET
    @Produces("application/json")
    public List <CommentJson> allComments (@PathParam("page") String page){
        List<Comment> comments = DaoFactory.getDaoCommentsInstance().selectPage(Integer.parseInt(page));
        ArrayList<CommentJson> commentJsons = new ArrayList<>();

        for (Comment c : comments) {
            commentJsons.add(new CommentJson(c));
        }

        return commentJsons;
    }


    @GET
    @Path("sort/{page}/{byWhat}/{order}")
    @Produces("application/json")
    public ArrayList<CommentJson> sort(@PathParam("page")String page,@PathParam("byWhat") String byWhat,@PathParam("order") String order) {
        ArrayList<CommentJson> commentJsons = new ArrayList<CommentJson>();
        List<Comment> comments =DaoFactory.getDaoCommentsInstance().orderBy(byWhat,Integer.parseInt(page),Boolean.valueOf(order));
        for(Comment comment : comments){
            commentJsons.add(new CommentJson(comment));
        }
        return commentJsons;
    }
}
