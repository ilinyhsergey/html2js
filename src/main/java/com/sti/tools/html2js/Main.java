package com.sti.tools.html2js;

import java.io.*;

public class Main {

    public static void execute(String[] args) throws IOException, IllegalArgumentException {

        Arguments arguments = new Arguments().fulfill(args);

//        System.out.println(arguments);

        String template = readTemplate(arguments);

        System.out.println(template);

    }

    private static String readTemplate(Arguments arguments) throws IOException {
        StringBuilder template = new StringBuilder();
        String line;
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(new File(arguments.templateName)));
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

}
