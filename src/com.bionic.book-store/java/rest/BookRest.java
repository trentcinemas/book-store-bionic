package rest;

import entities.Book;
import entities.PurchasedBook;
import entities.User;
import jsonClasses.BookJson;
import util.DaoFactory;
import util.FileUpload;
import util.Logger;
import util.MultipartRequestMap;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Path("book/")
@MultipartConfig(location = "/upload", maxFileSize = 10485760L) // 10MB.
public class BookRest extends HttpServlet {
    private final String RESPONSE_HEADER = "Access-Control-Allow-Origin";

    @Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @POST
    public Response addBook(@CookieParam("user") String enteredUser, @Context HttpServletRequest request) throws Exception {

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


        String mainPath = FileUpload.SERVER_UPLOAD_LOCATION_FOLDER + "BOOKS\\" + enBook.getTitle();
        File myDir = new File(mainPath);
        myDir.mkdirs();

        Part photoPart = request.getPart("photo");
        Part pdfPart = request.getPart("pdf");
        Part docPart = request.getPart("doc");
        Part fb2Part = request.getPart("fb2");
        InputStream photoStream = photoPart.getInputStream();
        InputStream pdfStream = pdfPart.getInputStream();
        InputStream docStream =docPart.getInputStream();
        InputStream fb2Stream =fb2Part.getInputStream();
        String photoPath = mainPath + "\\" + photoPart.getSubmittedFileName();
        String pdfPath = mainPath+"\\"+pdfPart.getSubmittedFileName();
        String docPath = mainPath+"\\"+docPart.getSubmittedFileName();
        String fb2Path = mainPath+"\\"+fb2Part.getSubmittedFileName();


        FileUpload.saveFile(photoStream, photoPath);
        FileUpload.saveFile(pdfStream,pdfPath);
        FileUpload.saveFile(docStream,docPath);
        FileUpload.saveFile(fb2Stream,fb2Path);

        enBook.setCover(photoPath);
        enBook.setPath(mainPath);

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

    @GET
    @Path("getUserBooks")
    @Produces("application/json")
    public ArrayList<BookJson> getAllBooks(@CookieParam("user") String userEmail) {
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
