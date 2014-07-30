package jsonClasses;

import entities.Author;

/**
 * Created by jsarafajr on 29.07.14.
 */
public class AuthorJson {
    private String id;
    private String firstName;
    private String lastName;
    private String description;
    private String photo;

    public AuthorJson(Author author) {
        if (author == null) {
            id = Integer.toString(0);
            firstName = null;
            lastName = null;
            description = null;
            photo = null;
        } else {
            id = Integer.toString(author.getAuthorId());
            firstName = author.getFirstname();
            lastName = author.getLastname();
            description = author.getDescription();
            photo = author.getPhoto();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
