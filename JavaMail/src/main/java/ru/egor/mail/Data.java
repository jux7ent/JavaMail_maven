package ru.egor.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.Scanner;


/**
 * В этом классе получаем данные из внешних файлов и командной строки
 */

class Data {
    private SettingsName sn;
    private Properties props;
    private ParsePattern pp;
    private String login;

    Data(String[] args) throws IOException {
        sn = new SettingsName(args);
        props = new Properties();

        setProps();

        pp = new ParsePattern(sn.getTemplateName(), props);
    }

    private void setProps() throws IOException {
        try (FileInputStream cfg = new FileInputStream(sn.getConfigName())) {
            props.load(new InputStreamReader(cfg, Charset.forName("UTF-8")));
        }
    }

    String getLogin() {
        if (login == null) {
            login = props.getProperty("sender.login", "none");

            if (login.equals("none")) {
                login = setLogin();
            }

            return login;
        }
        else {
            return login;
        }
    }

    String getPassword() {
        String password = props.getProperty("sender.password", "none");

        if (password.equals("none")) {
            password = setPassword();
        }

        return password;
    }

    Properties getProps() {
        return props;
    }

    String getTitle() {
        return pp.getTitle();
    }

    String getBody() {
        return pp.getBody();
    }

    private String setLogin() {
        System.out.print("Input login: ");

        return new Scanner(System.in).nextLine();
    }

    private String setPassword() {
        System.out.print("Input password: ");

        return new Scanner(System.in).nextLine();
    }
}
