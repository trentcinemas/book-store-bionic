package jsonClasses;

import entities.Author;
import entities.PurchasedBook;

/**
 * Created by jsarafajr on 05.08.14.
 */
public class PurchasedBookJson {
    private int id;
    private String user;
    private String book_title;
    private String book_author;
    private String date;
    private String price;

    public PurchasedBookJson(PurchasedBook book) {
        if (book == null) {
            id = 0;
            user = null;
            book_title = null;
            book_author = null;
            date = null;
        } else {
            id = book.getPurId();
            user = book.getUserByUserId().getEmail();
            book_title = book.getBookByBookId().getTitle();
            Author author = book.getBookByBookId().getAuthorByAuthorId();
            book_author = author.getFirstname() + " " + author.getLastname();
            date = book.getDate().toString();
            price = String.valueOf(book.getBookByBookId().getPrice());
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
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
}
