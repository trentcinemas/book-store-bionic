package rest;

import dao.DaoBook;
import entities.Author;
import entities.Book;
import jsonClasses.BookJson;
import org.hibernate.*;
import org.hibernate.Session;
import util.DaoFactory;
import util.HibernateUtil;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eklerka on 7/26/2014.
 */
@Path("search")
public class Search {

    private final String RESPONSE_HEADER = "Access-Control-Allow-Origin";
//    @Produces(MediaType.APPLICATION_JSON)
//    @Path("string")
    public ArrayList<BookJson> search(String s) {
        String[] words = s.split(" ");
        List<entities.Book> books = DaoFactory.getDaoBookInstance().search(s);
        ArrayList<BookJson> result = new ArrayList<BookJson>();
        for (entities.Book b : books){
            BookJson boook=new BookJson(b);
            result.add(boook);
        }

        return result;
    }
    //
//        @GET
//        public Response addBookToStorage(@CookieParam("user") String enteredUser) {
//            User user = DaoFactory.getDaoUserInstance().selectByEmail(enteredUser);
//            if (!checkUser(user)) {
//                Logger.log(Logger.Type.PROCESS, "Access denied : " + enteredUser);
//                return Response.status(403).header(RESPONSE_HEADER, "*")
//                        .entity("Вибачте, ви не маєте досупу до даної операції").build();
//            }
//
//            //...
//
//            return null;
//
//        }
//    @GET
//    public List<Book> searchBook(String searchPhrase) {
//
//        String[] searchWords = searchPhrase.split(" ");
//        User user = DaoFactory.getDaoUserInstance().selectByEmail(searchPhrase);
//        if (!checkUser(user)) {
//            Logger.log(Logger.Type.PROCESS, "Access denied : " + searchPhrase);
//            return Response.status(403).header(RESPONSE_HEADER, "*")
//                    .entity("Вибачте, ви не маєте досупу до даної операції").build();
//        }
//        return null;
//
//    }
//        private boolean checkUser(User user) {
//            if (user == null) return false;
//            // TODO check user group
//
//            return true;
//        }
}