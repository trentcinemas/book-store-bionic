package rest;


import entities.Book;
import entities.User;
import util.CheckUser;
import util.DaoFactory;
import util.MultipartRequestMap;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * Created by Джон on 29.07.2014.
 */
@Path("file")
public class FileRest {
    private static final String FILE_BOOK_PATH= MultipartRequestMap.UPLOAD_PATH + "/";
    @GET
    @Path("getauthorimage/{id}")
    @Produces("image/*")
    public Response getAuthorImage(@PathParam("id") String id){
        File file = new File(FILE_BOOK_PATH+ DaoFactory.getDaoAuthorInstance().selectById(Integer.parseInt(id)).getPhoto());
        return Response.ok(file).build();
    }
    @GET
    @Path("getsmallimage/{id}")
    @Produces("image/*")
    public Response getImage(@PathParam("id")String id){
        File file = new File(FILE_BOOK_PATH+ DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getCover());
        return Response.ok(file).build();
    }
    @GET
    @Path("getbigimage/{id}")
    @Produces("images/*")
    public Response getBigImage(@PathParam("id")String id){
        File file = new File(FILE_BOOK_PATH+ DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getBigCover());
        return Response.ok(file).build();
    }

    @GET
    @Path("getreviewfile/{id}")
    @Produces("application/pdf")
    public Response getPreview(@PathParam("id")String id){
        File file = new File(FILE_BOOK_PATH+ DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getPreview());
        return Response.ok(file).build();
    }

    @GET
    @Path("getpdf/{id}")
    @Produces("application/pdf")
    public Response getPdf(@CookieParam("user")String email,@PathParam("id") String id){
        User user= DaoFactory.getDaoUserInstance().selectByEmail(email);
        Book book= DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id));
        if(user == null || book ==null) return Response.serverError().build();
        if(CheckUser.isAdmin(user) || DaoFactory.getDaoPurchasedBookInstance().selectByUser(user).contains(book)){
            File file = new File(FILE_BOOK_PATH+DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getPdfPath());
            return Response.ok(file).build();
        }
        else return Response.serverError().build();
    }
    @GET
    @Path("getdoc/{id}")
    @Produces("*/*")
    public Response getDoc(@CookieParam("user")String email,@PathParam("id") String id){
        User user= DaoFactory.getDaoUserInstance().selectByEmail(email);
        Book book= DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id));
        if(user == null || book ==null) return Response.serverError().build();
        if(CheckUser.isAdmin(user) || DaoFactory.getDaoPurchasedBookInstance().selectByUser(user).contains(book)){
            File file = new File(FILE_BOOK_PATH+DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getDocPath());
            return Response.ok(file).build();
        }
        else return Response.serverError().build();
    }
    @GET
    @Path("getfb2/{id}")
    @Produces("*/*")
    public Response getfb2(@CookieParam("user")String email,@PathParam("id") String id){
        User user= DaoFactory.getDaoUserInstance().selectByEmail(email);
        Book book= DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id));
        if(user == null || book ==null) return Response.serverError().build();
        if(CheckUser.isAdmin(user) || DaoFactory.getDaoPurchasedBookInstance().selectByUser(user).contains(book)){
            File file = new File(FILE_BOOK_PATH+DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getFb2Path());
            return Response.ok(file).build();
        }
        else return Response.serverError().build();
    }
}
