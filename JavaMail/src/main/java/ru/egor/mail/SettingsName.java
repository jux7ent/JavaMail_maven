package ru.egor.mail;

class SettingsName {
    private final String CONF_FILE_NAME, TEMPLATE_FILE_NAME;

    SettingsName(String[] args) {
        if (args == null || args.length == 0) {
            CONF_FILE_NAME = "props.properties";
            TEMPLATE_FILE_NAME = "template.txt";
        }
        else if (args.length == 1) {
            CONF_FILE_NAME = args[0];
            TEMPLATE_FILE_NAME = "template.txt";
        }
        else {
            CONF_FILE_NAME = args[0];
            TEMPLATE_FILE_NAME = args[1];
        }
    }

    String getConfigName() {
        return CONF_FILE_NAME;
    }

    String getTemplateName() {
        return TEMPLATE_FILE_NAME;
    }
}
