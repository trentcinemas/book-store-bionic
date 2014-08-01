package jsonClasses;

/**
 * Created by jsarafajr on 23.07.14.
 */
public class UserJson {
    private int id;
    private String email;
    private String name;
    private String group;

    public UserJson(entities.User user) {
        if (user == null) {
            this.id = 0;
            this.email = null;
            this.name = null;
            this.group = null;
        } else {
            this.id = user.getUserId();
            this.email = user.getEmail();
            this.name = user.getName();
            this.group = user.getUserGroupByGroupId().getType();
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
