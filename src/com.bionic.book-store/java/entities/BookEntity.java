package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by jsarafajr on 16.07.14.
 */
@Entity
@Table(name = "Book", schema = "", catalog = "favorite_bs")
public class BookEntity {
    private int bookId;
    private String title;
    private Timestamp datePub;
    private Integer downloadsCnt;
    private Integer reviewCnt;
    private Integer pagesCnt;
    private String description;
    private String cover;
    private AuthorEntity authorByAutorId;
    private UserEntity userByUserId;
    private GenreEntity genreByGenreId;
    private DistributorEntity distributorByDistrId;
    private Collection<CommentEntity> commentsByBookId;
    private Collection<PurchasedBookEntity> purchasedBooksByBookId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (bookId != that.bookId) return false;
        if (cover != null ? !cover.equals(that.cover) : that.cover != null) return false;
        if (datePub != null ? !datePub.equals(that.datePub) : that.datePub != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (downloadsCnt != null ? !downloadsCnt.equals(that.downloadsCnt) : that.downloadsCnt != null) return false;
        if (pagesCnt != null ? !pagesCnt.equals(that.pagesCnt) : that.pagesCnt != null) return false;
        if (reviewCnt != null ? !reviewCnt.equals(that.reviewCnt) : that.reviewCnt != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

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
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "autor_id", referencedColumnName = "author_id")
    public AuthorEntity getAuthorByAutorId() {
        return authorByAutorId;
    }

    public void setAuthorByAutorId(AuthorEntity authorByAutorId) {
        this.authorByAutorId = authorByAutorId;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "usr_id")
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "genre_id")
    public GenreEntity getGenreByGenreId() {
        return genreByGenreId;
    }

    public void setGenreByGenreId(GenreEntity genreByGenreId) {
        this.genreByGenreId = genreByGenreId;
    }

    @ManyToOne
    @JoinColumn(name = "distr_id", referencedColumnName = "distr_id")
    public DistributorEntity getDistributorByDistrId() {
        return distributorByDistrId;
    }

    public void setDistributorByDistrId(DistributorEntity distributorByDistrId) {
        this.distributorByDistrId = distributorByDistrId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<CommentEntity> getCommentsByBookId() {
        return commentsByBookId;
    }

    public void setCommentsByBookId(Collection<CommentEntity> commentsByBookId) {
        this.commentsByBookId = commentsByBookId;
    }

    @OneToMany(mappedBy = "bookByBookId")
    public Collection<PurchasedBookEntity> getPurchasedBooksByBookId() {
        return purchasedBooksByBookId;
    }

    public void setPurchasedBooksByBookId(Collection<PurchasedBookEntity> purchasedBooksByBookId) {
        this.purchasedBooksByBookId = purchasedBooksByBookId;
    }
}
