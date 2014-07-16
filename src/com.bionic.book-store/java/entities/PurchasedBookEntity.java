package entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by jsarafajr on 16.07.14.
 */
@Entity
@Table(name = "PurchasedBook", schema = "", catalog = "favorite_bs")
public class PurchasedBookEntity {
    private int purId;
    private Timestamp date;
    private UserEntity userByUsrId;
    private BookEntity bookByBookId;

    @Id
    @Column(name = "pur_id")
    public int getPurId() {
        return purId;
    }

    public void setPurId(int purId) {
        this.purId = purId;
    }

    @Basic
    @Column(name = "date")
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

        PurchasedBookEntity that = (PurchasedBookEntity) o;

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
    @JoinColumn(name = "usr_id", referencedColumnName = "usr_id")
    public UserEntity getUserByUsrId() {
        return userByUsrId;
    }

    public void setUserByUsrId(UserEntity userByUsrId) {
        this.userByUsrId = userByUsrId;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    public BookEntity getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(BookEntity bookByBookId) {
        this.bookByBookId = bookByBookId;
    }
}
