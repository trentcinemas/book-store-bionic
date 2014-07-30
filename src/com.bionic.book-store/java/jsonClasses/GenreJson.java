package jsonClasses;

import entities.Genre;

/**
 * Created by Джон on 30.07.2014.
 */
public class GenreJson {
    String id;
    String type;

    public GenreJson(Genre genre) {
        if(genre==null){
            this.id=null;
            this.type=genre.getType();
        }
        else {
            this.id = Integer.toString(genre.getGenreId());
            this.type = genre.getType();
        }
    }

    public GenreJson(String id, String type) {
        this.id = id;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
