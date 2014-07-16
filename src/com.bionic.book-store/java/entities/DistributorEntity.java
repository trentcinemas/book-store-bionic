package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jsarafajr on 16.07.14.
 */
@Entity
@Table(name = "Distributor", schema = "", catalog = "favorite_bs")
public class DistributorEntity {
    private int distrId;
    private String url;
    private Collection<BookEntity> booksByDistrId;

    @Id
    @Column(name = "distr_id")
    public int getDistrId() {
        return distrId;
    }

    public void setDistrId(int distrId) {
        this.distrId = distrId;
    }

    @Basic
    @Column(name = "url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistributorEntity that = (DistributorEntity) o;

        if (distrId != that.distrId) return false;
        if (url != null ? !url.equals(that.url) : that.url != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = distrId;
        result = 31 * result + (url != null ? url.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "distributorByDistrId")
    public Collection<BookEntity> getBooksByDistrId() {
        return booksByDistrId;
    }

    public void setBooksByDistrId(Collection<BookEntity> booksByDistrId) {
        this.booksByDistrId = booksByDistrId;
    }
}
