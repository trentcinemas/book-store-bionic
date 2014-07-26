package jsonClasses;

/**
 * Created by jsarafajr on 23.07.14.
 */
public class UserJson {
    private String email;
    private String name;

    public UserJson(entities.User user) {
        if (user == null) {
            this.email = null;
            this.name = null;
        } else {
            this.email = user.getEmail();
            this.name = user.getName();
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
}
