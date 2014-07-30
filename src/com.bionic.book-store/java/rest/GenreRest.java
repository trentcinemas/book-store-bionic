package rest;

import entities.Genre;
import jsonClasses.GenreJson;
import util.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Джон on 30.07.2014.
 */
@Path("genre")
public class GenreRest {
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
