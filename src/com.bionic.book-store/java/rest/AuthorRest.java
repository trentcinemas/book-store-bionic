package rest;

import entities.Author;
import entities.User;
import jsonClasses.AuthorJson;
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
import java.util.ArrayList;
import java.util.List;

import static util.Logger.Type.*;

/**
 * Created by jsarafajr on 29.07.14.
 */

@Path("author")
@MultipartConfig(location = "/add", maxFileSize = 10485760L) // 10MB.
public class AuthorRest extends HttpServlet {
    public static String PHOTO_DIRECTORY_PATH = MultipartRequestMap.UPLOAD_PATH + "/_authors/";

    @POST
    @Path("add")
    public Response addAuthor(@CookieParam("user") String userEmail,
                              @Context HttpServletRequest request) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (!checkUser(user)) {
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

    @GET
    @Path("photo{id}")
    @Produces("image/*")
    public Response getImage(@PathParam("id") String id) {
        Integer authorId = Integer.parseInt(id);
        Author author = DaoFactory.getDaoAuthorInstance().selectById(authorId);

        File image = new File(PHOTO_DIRECTORY_PATH + author.getPhoto());

        return Response.ok(image).build();
    }


    /**
     * Returns true if user has access
     * @param user Entity User object
     * @return boolean status
     */
    private boolean checkUser(User user) {
        if (user == null) return false;
        // TODO check user group

        return true;
    }
}
