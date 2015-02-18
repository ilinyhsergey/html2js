package com.sti.tools.html2js;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

    public static boolean isEmptyOrNull(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static String readFile(String fileName) throws IOException {
        StringBuilder template = new StringBuilder();
        String line;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(new File(fileName)));
            while ((line = reader.readLine()) != null) {
                template.append(line).append('\n');
            }
            reader.close();
            reader = null;
        } finally {
            if (reader != null)
                reader.close();
        }

        return template.toString();
    }

    public static StringBuffer replace(StringBuffer buffer, String template, String replaceContent, Pattern pattern) {
        Matcher m = pattern.matcher(template);
        while (m.find()) {
            m.appendReplacement(buffer, replaceContent);
        }
        m.appendTail(buffer);
        return buffer;
    }

}
