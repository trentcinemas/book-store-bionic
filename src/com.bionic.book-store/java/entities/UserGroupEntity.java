package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jsarafajr on 16.07.14.
 */
@Entity
@Table(name = "UserGroup", schema = "", catalog = "favorite_bs")
public class UserGroupEntity {
    private int uesrGroupId;
    private String type;
    private Collection<UserEntity> usersByUesrGroupId;

    @Id
    @Column(name = "uesrGroup_id")
    public int getUesrGroupId() {
        return uesrGroupId;
    }

    public void setUesrGroupId(int uesrGroupId) {
        this.uesrGroupId = uesrGroupId;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserGroupEntity that = (UserGroupEntity) o;

        if (uesrGroupId != that.uesrGroupId) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = uesrGroupId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userGroupByGroupId")
    public Collection<UserEntity> getUsersByUesrGroupId() {
        return usersByUesrGroupId;
    }

    public void setUsersByUesrGroupId(Collection<UserEntity> usersByUesrGroupId) {
        this.usersByUesrGroupId = usersByUesrGroupId;
    }
}
