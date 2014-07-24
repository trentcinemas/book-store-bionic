package entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jsarafajr on 17.07.14.
 */
@Entity
public class PurchasedBook {
    private int purId;
    private Timestamp date;
    private User userByUserId;
    private Book bookByBookId;

    @Id
    @Column(name = "pur_id", nullable = false, insertable = true, updatable = true)
    public int getPurId() {
        return purId;
    }

    public void setPurId(int purId) {
        this.purId = purId;
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

        PurchasedBook that = (PurchasedBook) o;

        if (purId != that.purId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = purId;
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
