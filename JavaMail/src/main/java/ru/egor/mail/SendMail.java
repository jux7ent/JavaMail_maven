package ru.egor.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

public class SendMail {
    public static void send(String[] args) throws IOException {
        final Data data = new Data(args);

        Session session;
        session = Session.getInstance(data.getProps(),
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(data.getLogin(),
                                data.getPassword());
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(data.getLogin()));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(data.getProps().getProperty("receivers.addresses")));

            message.setSubject(data.getTitle());
            message.setText(data.getBody());

            Transport.send(message);

            System.out.println("Email sent.");

        } catch (MessagingException e) {
            System.err.println("Failed to sent email.");
            throw new RuntimeException(e);
        }
    }
}
