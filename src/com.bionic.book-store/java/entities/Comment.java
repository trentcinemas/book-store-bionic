package entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jsarafajr on 17.07.14.
 */
@Entity
public class Comment {
    private int commId;
    private Timestamp date;
    private User userByUserId;
    private Book bookByBookId;

    @Id
    @Column(name = "comm_id", nullable = false, insertable = true, updatable = true)
    public int getCommId() {
        return commId;
    }

    public void setCommId(int commId) {
        this.commId = commId;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commId != comment.commId) return false;
        if (date != null ? !date.equals(comment.date) : comment.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = commId;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    public User getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(User userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}
