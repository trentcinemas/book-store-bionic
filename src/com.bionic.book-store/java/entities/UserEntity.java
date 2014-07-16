package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jsarafajr on 16.07.14.
 */
@Entity
@Table(name = "User", schema = "", catalog = "favorite_bs")
public class UserEntity {
    private int usrId;
    private String email;
    private String password;
    private String name;
    private Collection<BookEntity> booksByUsrId;
    private Collection<CommentEntity> commentsByUsrId;
    private Collection<PurchasedBookEntity> purchasedBooksByUsrId;
    private UserGroupEntity userGroupByGroupId;

    @Id
    @Column(name = "usr_id")
    public int getUsrId() {
        return usrId;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
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

        UserEntity that = (UserEntity) o;

        if (usrId != that.usrId) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = usrId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<BookEntity> getBooksByUsrId() {
        return booksByUsrId;
    }

    public void setBooksByUsrId(Collection<BookEntity> booksByUsrId) {
        this.booksByUsrId = booksByUsrId;
    }

    @OneToMany(mappedBy = "userByUserId")
    public Collection<CommentEntity> getCommentsByUsrId() {
        return commentsByUsrId;
    }

    public void setCommentsByUsrId(Collection<CommentEntity> commentsByUsrId) {
        this.commentsByUsrId = commentsByUsrId;
    }

    @OneToMany(mappedBy = "userByUsrId")
    public Collection<PurchasedBookEntity> getPurchasedBooksByUsrId() {
        return purchasedBooksByUsrId;
    }

    public void setPurchasedBooksByUsrId(Collection<PurchasedBookEntity> purchasedBooksByUsrId) {
        this.purchasedBooksByUsrId = purchasedBooksByUsrId;
    }

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "uesrGroup_id")
    public UserGroupEntity getUserGroupByGroupId() {
        return userGroupByGroupId;
    }

    public void setUserGroupByGroupId(UserGroupEntity userGroupByGroupId) {
        this.userGroupByGroupId = userGroupByGroupId;
    }
}
