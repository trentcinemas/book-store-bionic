package entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by jsarafajr on 17.07.14.
 */
@Entity
public class UserGroup {
    private int userGroupId;
    private String type;
    private Collection<User> usersByUserGroupId;

    @Id
    @Column(name = "userGroup_id")
    public int getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(int userGroupId) {
        this.userGroupId = userGroupId;
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

        UserGroup userGroup = (UserGroup) o;

        if (userGroupId != userGroup.userGroupId) return false;
        if (type != null ? !type.equals(userGroup.type) : userGroup.type != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userGroupId;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "userGroupByGroupId")
    public Collection<User> getUsersByUserGroupId() {
        return usersByUserGroupId;
    }

    public void setUsersByUserGroupId(Collection<User> usersByUserGroupId) {
        this.usersByUserGroupId = usersByUserGroupId;
    }
}
