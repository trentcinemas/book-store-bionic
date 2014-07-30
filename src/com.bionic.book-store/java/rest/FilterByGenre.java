package rest;

import com.mysql.fabric.Response;
import entities.Book;
import jsonClasses.BookJson;
import util.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by UFO on 30.07.2014.
 */
@Path("filter/")
public class FilterByGenre {

    @GET
    @Path("byGenre/{id}")
    @Produces("application/json")
    public List<BookJson> filterByGenre(@PathParam("id")String id){
        List <Book> books = DaoFactory.getDaoBookInstance().selectByGenreID(Integer.parseInt(id));
        List <BookJson> booksJson = new LinkedList<BookJson>();

        for (entities.Book book:books){
            BookJson bookJson = new BookJson(book);
            booksJson.add(bookJson);
        }
        return booksJson;
    }


}
