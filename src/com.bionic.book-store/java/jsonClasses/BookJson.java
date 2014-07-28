package jsonClasses;

import entities.Book;
import entities.PurchasedBook;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Джон on 25.07.2014.
 */
@XmlRootElement(name = "JBook")
public class BookJson {
    String title;
    String description;
    double price;
    String cover;

    public BookJson(Book book) {
        if (book == null) {
            this.title = null;
            this.description = null;
            this.price = 0;
            this.cover = null;
        } else {
            this.title = book.getTitle();
            this.description = book.getDescription();
            this.price = book.getPrice();
            this.cover = book.getCover();
        }
    }

    public BookJson(PurchasedBook book) {
        if (book == null) {
            this.title = null;
            this.description = null;
            this.price = 0;
            this.cover = null;
        } else {
            this.title = book.getBookByBookId().getTitle();
            this.description = book.getBookByBookId().getDescription();
            this.price = book.getBookByBookId().getPrice();
            this.cover = book.getBookByBookId().getCover();
        }
    }

    public BookJson() {}

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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
