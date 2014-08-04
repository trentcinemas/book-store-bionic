package util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by Джон on 02.08.2014.
 */
public class MailService {
public static void send(String emailto,String subject,String messageText){
    final String username = "sbookbionic@gmail.com";
    final String password = "bookpass";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(emailto));
        message.setSubject(subject);
        message.setText(messageText);
        Transport.send(message);

        Logger.log(Logger.Type.PROCESS,"Message send from "+username+" to "+emailto);

    } catch (MessagingException e) {
        throw new RuntimeException(e);
    }
}
}
