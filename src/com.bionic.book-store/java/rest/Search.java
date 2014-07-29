package rest;

import dao.DaoBook;
import entities.Author;
import entities.Book;
import jsonClasses.BookJson;
import org.hibernate.*;
import org.hibernate.Session;
import util.DaoFactory;
import util.HibernateUtil;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Eklerka on 7/26/2014.
 */
@Path("book/")
public class Search {

    @Path("search")
    @GET
    @Produces("application/json")
    public ArrayList<BookJson> search(String s) {
        String[] words = s.split(" ");
        List<Book> books = DaoFactory.getDaoBookInstance().search(s);

        ArrayList<BookJson> result = new ArrayList<BookJson>();
        for (Book b : books){
            BookJson book = new BookJson(b);
            result.add(book);
        }
        return result;
    }
}