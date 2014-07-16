package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int id;

    private String email;

    private String password;

    private String name;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @Column(name = "group_id")
    private UserGroup group;

    @OneToMany(mappedBy = "user_id",targetEntity = Comment.class, fetch = FetchType.EAGER)
    private List<Comment> comments;

    @OneToMany(mappedBy = "user_id", targetEntity = Comment.class,fetch = FetchType.EAGER)
    private List<Book> books;
}
