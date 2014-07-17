package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jsarafajr on 17.07.14.
 */
@Entity
public class User {
    private int userId;
    private String email;
    private String password;
    private String name;
    private Collection<Book> booksByUserId;
    private Collection<Comment> commentsByUserId;
    private Collection<PurchasedBook> purchasedBooksByUserId;
    private UserGroup userGroupByGroupId;

    @Id
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (userId != user.userId) return false;
        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return userId + " " +
                email + " " +
                name;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Book> getBooksByUserId() {
        return booksByUserId;
    }

    public void setBooksByUserId(Collection<Book> booksByUserId) {
        this.booksByUserId = booksByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<Comment> getCommentsByUserId() {
        return commentsByUserId;
    }

    public void setCommentsByUserId(Collection<Comment> commentsByUserId) {
        this.commentsByUserId = commentsByUserId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<PurchasedBook> getPurchasedBooksByUserId() {
        return purchasedBooksByUserId;
    }

    public void setPurchasedBooksByUserId(Collection<PurchasedBook> purchasedBooksByUserId) {
        this.purchasedBooksByUserId = purchasedBooksByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "userGroup_id")
    public UserGroup getUserGroupByGroupId() {
        return userGroupByGroupId;
    }

    public void setUserGroupByGroupId(UserGroup userGroupByGroupId) {
        this.userGroupByGroupId = userGroupByGroupId;
    }
}
