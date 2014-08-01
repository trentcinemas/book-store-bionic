package jsonClasses;

import entities.ReplyMessage;

/**
 * Created by jsarafajr on 01.08.14.
 */
public class ReplyMessageJson {
    private int id;
    private String email;
    private String receiver;
    private String text;
    private String date;

    public ReplyMessageJson(ReplyMessage message) {
        if (message == null) {
            id = 0;
            email = null;
            receiver = null;
            text = null;
            date = null;
        } else {
            id = message.getMessageId();
            email = message.getEmail();
            receiver = message.getReceiver();
            text = message.getText();
            date = message.getDate().toString();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
