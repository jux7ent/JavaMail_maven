package ru.egor.mail;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.File;

public class Main {
    public static void main(String[] args) throws IOException {
    	SendMail.send(args);
    }
}
