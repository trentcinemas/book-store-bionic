package jsonClasses;

import entities.Book;
import entities.Comment;
import entities.User;

/**
 * Created by UFO on 03.08.2014.
 */
public class CommentJson {
    private String id;
    private String date;
    private String user;
    private String book;
    private String comm_desc;

    public CommentJson(Comment comment) {
        if (comment == null) {
            this.id=null;
            this.date = null;
            this.user = null;
            this.book = null;
            this.comm_desc = null;
        } else {
            this.id= Integer.toString(comment.getCommId());
            this.date = comment.getDate().toString();
            this.user = comment.getUserByUserId().getEmail();
            this.book =comment.getBookByBookId().getTitle();
            this.comm_desc = comment.getCommDesc();

        }
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getComm_desc() {
        return comm_desc;
    }

    public void setComm_desc(String comm_desc) {
        this.comm_desc = comm_desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
