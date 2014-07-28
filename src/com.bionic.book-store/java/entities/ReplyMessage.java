package entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by jsarafajr on 26.07.14.
 */
@Entity
public class ReplyMessage {
    private int messageId;
    private String email;
    private String receiver;
    private String text;
    private Timestamp date;
    private Byte removed;

    @Id
    @Column(name = "messageId", nullable = false, insertable = true, updatable = true)
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "receiver", nullable = true, insertable = true, updatable = true)
    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Basic
    @Column(name = "text", nullable = true, insertable = true, updatable = true)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "date", nullable = true, insertable = true, updatable = true)
    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Basic
    @Column(name = "removed", nullable = true, insertable = true, updatable = true)
    public Byte getRemoved() {
        return removed;
    }

    public void setRemoved(Byte removed) {
        this.removed = removed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReplyMessage that = (ReplyMessage) o;

        if (messageId != that.messageId) return false;
        if (date != null ? !date.equals(that.date) : that.date != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (receiver != null ? !receiver.equals(that.receiver) : that.receiver != null) return false;
        if (removed != null ? !removed.equals(that.removed) : that.removed != null) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = messageId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (removed != null ? removed.hashCode() : 0);
        return result;
    }
}
