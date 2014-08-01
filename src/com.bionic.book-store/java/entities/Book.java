package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by Eklerka on 7/29/2014.
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
    private double price;
    private String bigCover;
    private String pdfPath;
    private String docPath;
    private String fb2Path;
    private String preview;
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
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "big_cover")
    public String getBigCover() {
        return bigCover;
    }

    public void setBigCover(String bigCover) {
        this.bigCover = bigCover;
    }

    @Basic
    @Column(name = "pdf_path")
    public String getPdfPath() {
        return pdfPath;
    }

    public void setPdfPath(String pdfPath) {
        this.pdfPath = pdfPath;
    }

    @Basic
    @Column(name = "doc_path")
    public String getDocPath() {
        return docPath;
    }

    public void setDocPath(String docPath) {
        this.docPath = docPath;
    }

    @Basic
    @Column(name = "fb2_path")
    public String getFb2Path() {
        return fb2Path;
    }

    public void setFb2Path(String fb2Path) {
        this.fb2Path = fb2Path;
    }

    @Basic
    @Column(name = "preview")
    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;

        Book book = (Book) o;

        if (bookId != book.bookId) return false;
        if (Double.compare(book.price, price) != 0) return false;
        if (authorByAuthorId != null ? !authorByAuthorId.equals(book.authorByAuthorId) : book.authorByAuthorId != null)
            return false;
        if (bigCover != null ? !bigCover.equals(book.bigCover) : book.bigCover != null) return false;
        if (commentsByBookId != null ? !commentsByBookId.equals(book.commentsByBookId) : book.commentsByBookId != null)
            return false;
        if (cover != null ? !cover.equals(book.cover) : book.cover != null) return false;
        if (datePub != null ? !datePub.equals(book.datePub) : book.datePub != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (distributorByDistrId != null ? !distributorByDistrId.equals(book.distributorByDistrId) : book.distributorByDistrId != null)
            return false;
        if (docPath != null ? !docPath.equals(book.docPath) : book.docPath != null) return false;
        if (downloadsCnt != null ? !downloadsCnt.equals(book.downloadsCnt) : book.downloadsCnt != null) return false;
        if (fb2Path != null ? !fb2Path.equals(book.fb2Path) : book.fb2Path != null) return false;
        if (genreByGenreId != null ? !genreByGenreId.equals(book.genreByGenreId) : book.genreByGenreId != null)
            return false;
        if (pagesCnt != null ? !pagesCnt.equals(book.pagesCnt) : book.pagesCnt != null) return false;
        if (pdfPath != null ? !pdfPath.equals(book.pdfPath) : book.pdfPath != null) return false;
        if (preview != null ? !preview.equals(book.preview) : book.preview != null) return false;
        if (purchasedBooksByBookId != null ? !purchasedBooksByBookId.equals(book.purchasedBooksByBookId) : book.purchasedBooksByBookId != null)
            return false;
        if (reviewCnt != null ? !reviewCnt.equals(book.reviewCnt) : book.reviewCnt != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (userByUserId != null ? !userByUserId.equals(book.userByUserId) : book.userByUserId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = bookId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (datePub != null ? datePub.hashCode() : 0);
        result = 31 * result + (downloadsCnt != null ? downloadsCnt.hashCode() : 0);
        result = 31 * result + (reviewCnt != null ? reviewCnt.hashCode() : 0);
        result = 31 * result + (pagesCnt != null ? pagesCnt.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cover != null ? cover.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (bigCover != null ? bigCover.hashCode() : 0);
        result = 31 * result + (pdfPath != null ? pdfPath.hashCode() : 0);
        result = 31 * result + (docPath != null ? docPath.hashCode() : 0);
        result = 31 * result + (fb2Path != null ? fb2Path.hashCode() : 0);
        result = 31 * result + (preview != null ? preview.hashCode() : 0);
        result = 31 * result + (authorByAuthorId != null ? authorByAuthorId.hashCode() : 0);
        result = 31 * result + (userByUserId != null ? userByUserId.hashCode() : 0);
        result = 31 * result + (genreByGenreId != null ? genreByGenreId.hashCode() : 0);
        result = 31 * result + (distributorByDistrId != null ? distributorByDistrId.hashCode() : 0);
        result = 31 * result + (commentsByBookId != null ? commentsByBookId.hashCode() : 0);
        result = 31 * result + (purchasedBooksByBookId != null ? purchasedBooksByBookId.hashCode() : 0);
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
