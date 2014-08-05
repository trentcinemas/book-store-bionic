package rest;

import entities.Genre;
import entities.User;
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
    @Path("update")
    public Response update(@CookieParam("user") String userEmail,
                           @FormParam("id") String idString,
                           @FormParam("title") String newTitle) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);
        // TODO check user

        int id = Integer.parseInt(idString);
        Genre genre = DaoFactory.getDaoGenreInstance().selectById(id);
        String title = genre.getType();

        genre.setType(newTitle);
        DaoFactory.getDaoGenreInstance().update(genre);

        Logger.log(Logger.Type.PROCESS, "Title: \"" + title + "\" changed to \"" + genre.getType() + "\"");

        return Response.ok().build();
    }

    @POST
    @Path("remove")
    public Response removeGenre(@CookieParam("user") String userEmail,
                                @FormParam("id") String idString) {

        // TODO check user
        if(DaoFactory.getDaoUserInstance().selectByEmail(userEmail)==null){
            return Response.serverError().build();
        }

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
    @GET
    @Path("sort")
    public ArrayList<GenreJson> sort(@QueryParam("order")String order){
        ArrayList<GenreJson> genreJsons = new ArrayList<GenreJson>();
        List<Genre> genres = DaoFactory.getDaoGenreInstance().orderByType(Boolean.valueOf(order));

        for (entities.Genre genre : genres) {
            GenreJson genreJson = new GenreJson(genre);
            genreJsons.add(genreJson);
        }

        return genreJsons;
    }
}

