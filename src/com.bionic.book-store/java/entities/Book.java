package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by jsarafajr on 17.07.14.
 */
@Entity
public class Book {
    private int bookId;
    private String title;
    private Timestamp datePub;
    private Integer downloadsCnt;
    private Integer reviewCnt;
    private Integer pagesCnt;
    private String description;
    private String cover;
    private int price;
    private Author authorByAuthorId;
    private User userByUserId;
    private Genre genreByGenreId;
    private Distributor distributorByDistrId;
    private Collection<Comment> commentsByBookId;
    private Collection<PurchasedBook> purchasedBooksByBookId;

    @Id
    @Column(name = "book_id")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "date_pub")
    public Timestamp getDatePub() {
        return datePub;
    }

    public void setDatePub(Timestamp datePub) {
        this.datePub = datePub;
    }

    @Basic
    @Column(name = "downloads_cnt")
    public Integer getDownloadsCnt() {
        return downloadsCnt;
    }

    public void setDownloadsCnt(Integer downloadsCnt) {
        this.downloadsCnt = downloadsCnt;
    }

    @Basic
    @Column(name = "review_cnt")
    public Integer getReviewCnt() {
        return reviewCnt;
    }

    public void setReviewCnt(Integer reviewCnt) {
        this.reviewCnt = reviewCnt;
    }

    @Basic
    @Column(name = "pages_cnt")
    public Integer getPagesCnt() {
        return pagesCnt;
    }

    public void setPagesCnt(Integer pagesCnt) {
        this.pagesCnt = pagesCnt;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "cover")
    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Basic
    @Column(name = "price")
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (price != book.price) return false;
        if (cover != null ? !cover.equals(book.cover) : book.cover != null) return false;
        if (datePub != null ? !datePub.equals(book.datePub) : book.datePub != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (downloadsCnt != null ? !downloadsCnt.equals(book.downloadsCnt) : book.downloadsCnt != null) return false;
        if (pagesCnt != null ? !pagesCnt.equals(book.pagesCnt) : book.pagesCnt != null) return false;
        if (reviewCnt != null ? !reviewCnt.equals(book.reviewCnt) : book.reviewCnt != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = bookId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (datePub != null ? datePub.hashCode() : 0);
        result = 31 * result + (downloadsCnt != null ? downloadsCnt.hashCode() : 0);
        result = 31 * result + (reviewCnt != null ? reviewCnt.hashCode() : 0);
        result = 31 * result + (pagesCnt != null ? pagesCnt.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    public Author getAuthorByAuthorId() {
        return authorByAuthorId;
    }

    public void setAuthorByAuthorId(Author authorByAuthorId) {
        this.authorByAuthorId = authorByAuthorId;
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
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    public Genre getGenreByGenreId() {
        return genreByGenreId;
    }

    public void setGenreByGenreId(Genre genreByGenreId) {
        this.genreByGenreId = genreByGenreId;
    }

    @ManyToOne
    @JoinColumn(name = "distr_id", referencedColumnName = "distr_id")
    public Distributor getDistributorByDistrId() {
        return distributorByDistrId;
    }

    public void setDistributorByDistrId(Distributor distributorByDistrId) {
        this.distributorByDistrId = distributorByDistrId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<Comment> getCommentsByBookId() {
        return commentsByBookId;
    }

    public void setCommentsByBookId(Collection<Comment> commentsByBookId) {
        this.commentsByBookId = commentsByBookId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<PurchasedBook> getPurchasedBooksByBookId() {
        return purchasedBooksByBookId;
    }

    public void setPurchasedBooksByBookId(Collection<PurchasedBook> purchasedBooksByBookId) {
        this.purchasedBooksByBookId = purchasedBooksByBookId;
    }
}
