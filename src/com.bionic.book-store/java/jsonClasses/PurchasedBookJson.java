package jsonClasses;

import entities.Author;
import entities.PurchasedBook;

/**
 * Created by jsarafajr on 05.08.14.
 */
public class PurchasedBookJson {
    private String id;
    private String book_id;
    private String book_title;
    private String book_author;
    private String user;
    private String sm_cover;
    private String big_cover;
    private String date;
    private String price;

    public PurchasedBookJson(PurchasedBook book) {
        if (book == null) {
            id = null;
            book_id=null;
            user = null;
            book_title = null;
            book_author = null;
            date = null;
        } else {
            id = Integer.toString(book.getPurId());
            book_id=Integer.toString(book.getBookByBookId().getBookId());
            user = book.getUserByUserId().getEmail();
            book_title = book.getBookByBookId().getTitle();
            Author author = book.getBookByBookId().getAuthorByAuthorId();
            book_author = author.getFirstname() + " " + author.getLastname();
            date = book.getDate().toString();
            price = String.valueOf(book.getBookByBookId().getPrice());
            sm_cover=book.getBookByBookId().getCover();
            big_cover=book.getBookByBookId().getBigCover();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBook_id() {
        return book_id;
    }

    public void setBook_id(String book_id) {
        this.book_id = book_id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }
}
