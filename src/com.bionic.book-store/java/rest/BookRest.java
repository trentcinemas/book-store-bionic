package rest;

import entities.Book;
import entities.PurchasedBook;
import entities.User;
import jsonClasses.BookJson;
import util.DaoFactory;
import util.Logger;
import util.MultipartRequestMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("book/")
@MultipartConfig(location = "/upload", maxFileSize = 10485760L) // 10MB.
public class BookRest extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config); //added this line then it worked
    }

    private final String RESPONSE_HEADER = "Access-Control-Allow-Origin";

    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response addBook(@CookieParam("user") String enteredUser, @Context HttpServletRequest request) {

       User user = DaoFactory.getDaoUserInstance().selectByEmail(enteredUser);

        if (!checkUser(user)) {
            Logger.log(Logger.Type.PROCESS, "Access denied : " + enteredUser);
            return Response.status(403).header(RESPONSE_HEADER, "*")
                    .entity("Вибачте, ви не маєте досупу до даної операції").build();
        }

        MultipartRequestMap map = new MultipartRequestMap(request);
        entities.Book enBook = new entities.Book();
        enBook.setTitle(map.getStringParameter("title"));
        enBook.setDescription(map.getStringParameter("description"));
        enBook.setPrice(Float.parseFloat(map.getStringParameter("price")));
        enBook.setDatePub(new Timestamp(System.currentTimeMillis()));
        enBook.setUserByUserId(user);
        enBook.setReviewCnt(0);
        enBook.setDownloadsCnt(0);
        enBook.setPagesCnt(Integer.parseInt(map.getStringParameter("page_count")));
        enBook.setCover(map.getStringParameter("title")+"/"+map.getFileParameter("photo").getAbsoluteFile().getName());
        enBook.setPdfPath(map.getStringParameter("title")+"/"+map.getFileParameter("pdf").getAbsoluteFile().getName());
        enBook.setDocPath(map.getStringParameter("title")+"/"+map.getFileParameter("doc").getAbsoluteFile().getName());
        enBook.setFb2Path(map.getStringParameter("title")+"/"+map.getFileParameter("fb2").getAbsoluteFile().getName());

        DaoFactory.getDaoBookInstance().insert(enBook);

        return Response.ok().build();

    }

    @Path("listAll")
    @GET
    @Produces("application/json")
    public ArrayList<BookJson> getAllBooks() {

        ArrayList<BookJson> booksJson = new ArrayList<BookJson>();
        List<Book> books = DaoFactory.getDaoBookInstance().selectAll();

        for (entities.Book book : books) {
            BookJson bookJson = new BookJson(book);
            booksJson.add(bookJson);
        }

        return booksJson;
    }

    @Path("list/{limit}/{order}")
    @GET
    @Produces("application/json")
    public ArrayList<BookJson> GetLastAddedBooks(@PathParam("limit")String limit,@PathParam("order")String order) {

        ArrayList<BookJson> booksJson = new ArrayList<BookJson>();
        List<Book> books = DaoFactory.getDaoBookInstance().selectAllOrdered("datePub", Integer.parseInt(order) == 0 ? false : true);
        int lim=Integer.parseInt(limit);
        List<Book> orderedBooks=books.subList(0,lim>books.size() ? books.size() : lim);

        for (entities.Book book : books) {
            BookJson bookJson = new BookJson(book);
            booksJson.add(bookJson);
        }

        return booksJson;
    }


    @GET
    @Path("getUserBooks")
    @Produces("application/json")
    public ArrayList<BookJson> getUserBooks(@CookieParam("user") String userEmail) {
        if (userEmail == null) {
            return new ArrayList<>();
        }

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (!checkUser(user)) {
            return new ArrayList<>();
        }

        ArrayList<BookJson> booksJson = new ArrayList<BookJson>();
        List<PurchasedBook> purchasedBooks = DaoFactory.getDaoPurchasedBookInstance().selectByUser(user);

        for (entities.PurchasedBook purchasedBook : purchasedBooks) {
            BookJson bookJson = new BookJson(purchasedBook);
            booksJson.add(bookJson);
        }

        return booksJson;
    }

    @Path("search")
    @POST
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
    /**
     * Returns true if user has access
     * @param user
     * @return
     */
    private boolean checkUser(User user) {
        if (user == null) return false;
        // TODO check user group

        return true;
    }
}
