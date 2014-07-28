package jsonClasses;

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
