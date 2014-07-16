package entities;

import javax.persistence.*;
import java.awt.Image;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Book")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private int id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    private String description;

    private Image photo;

    @OneToMany(mappedBy = "author",targetEntity = Book.class, fetch = FetchType.EAGER)
    private List<Book> students;

    public Author() {}

    public Author(String firstName, String lastName, String description, Image photo, List<Book> students) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.photo = photo;
        this.students = students;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public List<Book> getStudents() {
        return students;
    }

    public void setStudents(List<Book> students) {
        this.students = students;
    }
}



