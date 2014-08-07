package jsonClasses;

import entities.Book;
import entities.PurchasedBook;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Джон on 25.07.2014.
 */
@XmlRootElement(name = "JBook")
public class BookJson {
    public String getAthorid() {
        return athorid;
    }

    public void setAthorid(String athorid) {
        this.athorid = athorid;
    }

    String athorid;
    String id;
    String title;
    String description;
    double price;
    String sm_cover;
    String big_cover;
    String author;
    String user;
    String date;
    String genre;
    int pagesCnt;
    int downloadCnt;
    int viewCnt;
    boolean newb;

    public BookJson(Book book) {
        if (book == null) {
            this.athorid=null;
            this.id=null;
            this.author=null;
            this.title = null;
            this.description = null;
            this.price = 0;
            this.sm_cover = null;
            this.big_cover=null;
            this.newb=false;

        } else {
            this.athorid=Integer.toString(book.getAuthorByAuthorId().getAuthorId());
            this.id=Integer.toString(book.getBookId());
            this.author=book.getAuthorByAuthorId().getFirstname()+" "+book.getAuthorByAuthorId().getLastname();
            this.title = book.getTitle();
            this.description = book.getDescription();
            this.price = book.getPrice();
            this.sm_cover = book.getCover();
            this.big_cover= book.getBigCover();

            this.user = book.getUserByUserId().getEmail();
            this.date = book.getDatePub().toString();
            this.genre = book.getGenreByGenreId().getType();
            this.pagesCnt = book.getPagesCnt();
            this.downloadCnt = book.getDownloadsCnt();
            this.viewCnt = book.getReviewCnt();
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BookJson(PurchasedBook book) {
        if (book == null) {
            this.id=Integer.toString(book.getBookByBookId().getBookId());
            this.title = null;
            this.description = null;
            this.price = 0;
            this.sm_cover = null;
            this.big_cover=null;
        } else {
            this.id=Integer.toString(book.getBookByBookId().getBookId());
            this.title = book.getBookByBookId().getTitle();
            this.description = book.getBookByBookId().getDescription();
            this.price = book.getBookByBookId().getPrice();
            this.sm_cover = book.getBookByBookId().getCover();
            this.big_cover= book.getBookByBookId().getBigCover();
        }
    }

    public BookJson() {}

    public String getSm_cover() {
        return sm_cover;
    }

    public void setSm_cover(String sm_cover) {
        this.sm_cover = sm_cover;
    }

    public String getBig_cover() {
        return big_cover;
    }

    public void setBig_cover(String big_cover) {
        this.big_cover = big_cover;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getPagesCnt() {
        return pagesCnt;
    }

    public void setPagesCnt(int pagesCnt) {
        this.pagesCnt = pagesCnt;
    }

    public int getDownloadCnt() {
        return downloadCnt;
    }

    public void setDownloadCnt(int downloadCnt) {
        this.downloadCnt = downloadCnt;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }
}
