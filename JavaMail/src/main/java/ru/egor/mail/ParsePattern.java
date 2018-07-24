package ru.egor.mail;

import java.io.*;
import java.nio.*;
import java.util.Properties;

class ParsePattern {
    private String title, body;
    private String templateFile;
    private Properties properties;

    ParsePattern(String templateFile, Properties properties) throws IOException {
        this.templateFile = templateFile;
        this.properties = properties;

        parsePattern();
    }

    String getTitle() {
        return title;
    }

    String getBody() {
        return body;
    }

    private void parsePattern() throws IOException {
        try (FileInputStream template = new FileInputStream(templateFile)) {
            BufferedReader br = new BufferedReader(new InputStreamReader(template, "UTF-8"));

            StringBuilder allFile = new StringBuilder();
            String line;

            while(( line = br.readLine()) != null ) {
                allFile.append(line);
                allFile.append('\n');
            }

            for (int index = allFile.indexOf("\\"); index != -1;
                    index = allFile.indexOf("\\", index)) {
                int endIndex = index + 1;

                while (Character.isLetter(allFile.charAt(endIndex))) {
                    ++endIndex;
                }

                String command = allFile.substring(index + 1, endIndex);

                command = properties.getProperty(command, command);

                allFile.replace(index, endIndex, command);

                ++index;
            }

            //title = <title>...</title>  <title>.length = 7
            title = allFile.substring(allFile.indexOf("<title>") + 7,
                    allFile.indexOf("</title>"));

            //body = <body>...</body>
            body = allFile.substring(allFile.indexOf("<body>") + 6,
                    allFile.indexOf("</body>"));
        }
    }
}
