package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jsarafajr on 17.07.14.
 */
@Entity
public class Author {
    private int authorId;
    private String firstname;
    private String lastname;
    private String description;
    private String photo;
    private Collection<Book> booksByAuthorId;

    @Id
    @Column(name = "author_id", nullable = false, insertable = true, updatable = true)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Basic
    @Column(name = "firstname", nullable = true, insertable = true, updatable = true, length = 45)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Basic
    @Column(name = "lastname", nullable = true, insertable = true, updatable = true, length = 45)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "description", nullable = true, insertable = true, updatable = true, length = 2147483647)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "photo", nullable = true, insertable = true, updatable = true, length = 255)
    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Author author = (Author) o;

        if (authorId != author.authorId) return false;
        if (description != null ? !description.equals(author.description) : author.description != null) return false;
        if (firstname != null ? !firstname.equals(author.firstname) : author.firstname != null) return false;
        if (lastname != null ? !lastname.equals(author.lastname) : author.lastname != null) return false;
        if (photo != null ? !photo.equals(author.photo) : author.photo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = authorId;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (photo != null ? photo.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "authorByAuthorId")
    public Collection<Book> getBooksByAuthorId() {
        return booksByAuthorId;
    }

    public void setBooksByAuthorId(Collection<Book> booksByAuthorId) {
        this.booksByAuthorId = booksByAuthorId;
    }
}
