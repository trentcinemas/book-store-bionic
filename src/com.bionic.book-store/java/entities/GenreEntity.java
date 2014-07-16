package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jsarafajr on 16.07.14.
 */
@Entity
@Table(name = "Genre", schema = "", catalog = "favorite_bs")
public class GenreEntity {
    private int genreId;
    private String type;
    private Collection<BookEntity> booksByGenreId;

    @Id
    @Column(name = "genre_id")
    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GenreEntity that = (GenreEntity) o;

        if (genreId != that.genreId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = genreId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "genreByGenreId")
    public Collection<BookEntity> getBooksByGenreId() {
        return booksByGenreId;
    }

    public void setBooksByGenreId(Collection<BookEntity> booksByGenreId) {
        this.booksByGenreId = booksByGenreId;
    }
}
