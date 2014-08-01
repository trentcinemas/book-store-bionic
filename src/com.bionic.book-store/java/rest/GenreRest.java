package rest;

import entities.Genre;
import jsonClasses.GenreJson;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Джон on 30.07.2014.
 */
@Path("genre")
public class GenreRest {
    @POST
    @Path("add")
    public Response addNewGenre(@CookieParam("user") String userEmail,
                                @FormParam("title") String title) {

        //TODO check user

        Genre genre = new Genre();
        genre.setType(title);
        DaoFactory.getDaoGenreInstance().insert(genre);

        Logger.log(Logger.Type.PROCESS, "Added genre : " + title);

        return Response.ok().build();
    }

    @POST
    @Path("remove")
    public Response removeGenre(@CookieParam("user") String userEmail,
                                @FormParam("id") String idString) {

        // TODO check user

        int id = Integer.parseInt(idString);
        String title = DaoFactory.getDaoGenreInstance().selectById(id).getType();
        DaoFactory.getDaoGenreInstance().delete(id);

        Logger.log(Logger.Type.PROCESS, "Remove genre : " + title);

        return Response.ok().build();
    }


    @GET
    @Path("getAll")
    @Produces("application/json")
    public ArrayList<GenreJson> getAll() {
        ArrayList<GenreJson> genreJsons = new ArrayList<GenreJson>();
        List<Genre> genres = DaoFactory.getDaoGenreInstance().selectAll();

        for (entities.Genre genre : genres) {
            GenreJson genreJson = new GenreJson(genre);
            genreJsons.add(genreJson);
        }

        return genreJsons;
    }
}
