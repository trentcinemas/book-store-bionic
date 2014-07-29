package jsonClasses;

import entities.Book;
import entities.PurchasedBook;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Джон on 25.07.2014.
 */
@XmlRootElement(name = "JBook")
public class BookJson {
    int id;
    String title;
    String description;
    double price;
    String sm_cover;
    String big_cover;

    public BookJson(Book book) {
        if (book == null) {
            this.id=0;
            this.title = null;
            this.description = null;
            this.price = 0;
            this.sm_cover = null;
            this.big_cover=null;
        } else {
            this.id=book.getBookId();
            this.title = book.getTitle();
            this.description = book.getDescription();
            this.price = book.getPrice();
            this.sm_cover = book.getCover();
            this.big_cover= book.getBigCover();
        }
    }

    public BookJson(PurchasedBook book) {
        if (book == null) {
            this.id=book.getBookByBookId().getBookId();
            this.title = null;
            this.description = null;
            this.price = 0;
            this.sm_cover = null;
            this.big_cover=null;
        } else {
            this.id=book.getBookByBookId().getBookId();
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
}
