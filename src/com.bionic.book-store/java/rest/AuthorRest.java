package rest;

import entities.Author;
import entities.Book;
import entities.User;
import jsonClasses.AuthorJson;
import jsonClasses.BookJson;
import util.CheckUser;
import util.DaoFactory;
import util.Logger;
import util.MultipartRequestMap;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static util.Logger.Type.PROCESS;

/**
 * Created by jsarafajr on 29.07.14.
 */

@Path("author")
@MultipartConfig(location = "/add", maxFileSize = 10485760L) // 10MB.
public class AuthorRest extends HttpServlet {
    public static String PHOTO_DIRECTORY_PATH = MultipartRequestMap.UPLOAD_PATH + "/_authors/";

    @POST
    @Path("add")
    @Consumes("multipart/form-data")
    public Response addAuthor(@CookieParam("user") String userEmail,
                              @Context HttpServletRequest request) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (!CheckUser.isAdmin(user) || !CheckUser.isModer(user) || !CheckUser.isRedactor(user)) {
            Logger.log(PROCESS, "Access denied : " + userEmail);
            return Response.status(403).entity("Вибачте, ви не маєте досупу до даної операції").build();
        }

        MultipartRequestMap map = new MultipartRequestMap(request, true);

        Author author = new Author();

        author.setFirstname(map.getStringParameter("au_firstname"));
        author.setLastname(map.getStringParameter("au_lastname"));
        author.setDescription(map.getStringParameter("au_description"));
        author.setPhoto(map.getFileParameter("au_photo").getAbsoluteFile().getName());

        DaoFactory.getDaoAuthorInstance().insert(author);

        Logger.log(PROCESS, "Author added : " + author.getFirstname() + " "
                + author.getLastname() + " by " + (user == null ? "null" : user.getEmail()));

        return Response.ok().build();
    }

    @POST
    @Path("update")
    @Consumes("multipart/form-data")
    public Response updateAuthor(@CookieParam("user") String userEmail,
                              @Context HttpServletRequest request) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (!CheckUser.isAdmin(user) && !CheckUser.isModer(user) && !CheckUser.isRedactor(user)) {
            Logger.log(PROCESS, "Access denied : " + userEmail);
            return Response.status(403).entity("Вибачте, ви не маєте досупу до даної операції").build();
        }

        MultipartRequestMap map = new MultipartRequestMap(request, true);

        Author author = DaoFactory.getDaoAuthorInstance().selectById(Integer.parseInt(map.getStringParameter("author_id")));

        author.setFirstname(map.getStringParameter("au_firstname"));
        author.setLastname(map.getStringParameter("au_lastname"));
        author.setDescription(map.getStringParameter("au_description"));

        if (map.getFileParameter("au_photo") != null) {
            author.setPhoto(map.getFileParameter("au_photo").getAbsoluteFile().getName());
        }

        DaoFactory.getDaoAuthorInstance().update(author);

        Logger.log(PROCESS, "Author updated: " + author.getFirstname() + " "
                + author.getLastname() + " by " + (user == null ? "null" : user.getEmail()));

        return Response.ok().build();
    }

    @GET
    @Path("get/{id}")
    @Produces("application/json")
    public AuthorJson getAuthor(@PathParam("id") String idString) {
        return new AuthorJson(
                DaoFactory.getDaoAuthorInstance().selectById(Integer.parseInt(idString)));
    }

    @GET
    @Path("getAll")
    @Produces("application/json")
    public ArrayList<AuthorJson> getAllAuthors() {
        List<Author> authors = DaoFactory.getDaoAuthorInstance().selectAll();
        ArrayList<AuthorJson> authorJsons = new ArrayList<>();

        for (Author a : authors) {
            authorJsons.add(new AuthorJson(a));
        }

        return authorJsons;
    }

    @POST
    @Path("remove")
    public Response removeAuthor(@FormParam("id") String idString) {
        int id = Integer.parseInt(idString);
        Author author = DaoFactory.getDaoAuthorInstance().selectById(id);

        String authorLog = author.getAuthorId() + " " + author.getFirstname() + " " + author.getLastname();
        String photoPath = AuthorRest.PHOTO_DIRECTORY_PATH + author.getPhoto();

        try {
            File photo = new File(photoPath);
            if (photo.delete()) {
                Logger.log(PROCESS, photoPath + " removed from storage");
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            Logger.log(PROCESS, photoPath + " remove failed!");
        }


        DaoFactory.getDaoAuthorInstance().delete(id);

        Logger.log(PROCESS, "Author removed : \"" + authorLog + "\"");

        return Response.ok().build();
    }

    @GET
    @Path("photo/{id}")
    @Produces("image/*")
    public Response getImage(@PathParam("id") String id) {
        Integer authorId = Integer.parseInt(id);
        Author author = DaoFactory.getDaoAuthorInstance().selectById(authorId);

        File image = new File(PHOTO_DIRECTORY_PATH + author.getPhoto());

        return Response.ok(image).build();
    }

    @GET
    @Path("getFromPage/{page}")
    @Produces("applicaion/json")
    public ArrayList<AuthorJson> getAuthors(@PathParam("page")String page){
        ArrayList<AuthorJson> authorJsons = new ArrayList<AuthorJson>();
        List<Author> authors = DaoFactory.getDaoAuthorInstance().selectPage(Integer.parseInt(page));

        for(Author author: authors){
            authorJsons.add(new AuthorJson(author));
        }
        return authorJsons;
    }
    @Path("getAuthPage/{id}")
    @GET
    public Response getSinglePage(@PathParam("id") String id){
        URI location;
        Author author =DaoFactory.getDaoAuthorInstance().selectById(Integer.parseInt(id));
        try {
            location = new URI("../single-author.html?id="+id);
        }
        catch(URISyntaxException e){
            return null;
        }
        return Response.temporaryRedirect(location).build();
    }




    @GET
    @Path("getPageCount")
    @Produces("text/plain")
    public String getPageCount(){
        return DaoFactory.getDaoAuthorInstance().count().toString();
    }


    @GET
    @Path("sort/{page}/{byWhat}/{order}")
    @Produces("application/json")
    public ArrayList<AuthorJson> sort(@PathParam("page")String page,@PathParam("byWhat") String byWhat,@PathParam("order") String order) {
        ArrayList<AuthorJson> authorJsons = new ArrayList<AuthorJson>();
        List<Author> authors = DaoFactory.getDaoAuthorInstance().orderBy(byWhat, Integer.parseInt(page), Boolean.valueOf(order));
        for(Author author : authors){
            authorJsons.add(new AuthorJson(author));
        }
        return authorJsons;
    }

    @GET
    @Path("search/{search}")
    @Produces("application/json")
    public ArrayList<AuthorJson> search(@PathParam("search") String search){
        ArrayList<AuthorJson> authorJsons = new ArrayList<AuthorJson>();
        List<Author> authors = DaoFactory.getDaoAuthorInstance().search(search);
        for(Author author : authors){
            authorJsons.add(new AuthorJson(author));
        }
        return authorJsons;
    }


}
