package ru.egor.mail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {
	File f = new File(System.getProperty("user.dir"));
        String[] list = f.list();

        for (String str : list) {
            System.out.println(str);
        }
        SendMail.send(args);
    }
}
