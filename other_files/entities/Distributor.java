package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "Distributor")
public class Distributor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "distr_id")
    private int id;

    private String url;

    @OneToMany(mappedBy = "distr_id", targetEntity = Book.class, fetch = FetchType.EAGER)
    private List<Book> books;

    public Distributor() {}

    public Distributor(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
