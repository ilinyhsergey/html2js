package com.sti.tools.html2js;

import java.io.*;
import java.util.regex.Pattern;

public class Main {

    public static final Pattern nameReplacePattern = Pattern.compile("\\{\\{(\\s*name\\s*)\\}\\}", Pattern.DOTALL);
    public static final Pattern contentReplacePattern = Pattern.compile("\\{\\{(\\s*content\\s*)\\}\\}", Pattern.DOTALL);

    public static void execute(String[] args) throws IOException, IllegalArgumentException {

        Arguments arguments = new Arguments().fulfill(args);

//        System.out.println(arguments);

        String template = readTemplate(arguments);

        Template t = Template.parse(template);

        System.out.println(t);
        // todo итерация по файлам источникам, замена, сборка.
/*
        Matcher m = replacePattern.matcher(template);
        StringBuffer b = new StringBuffer();

        while(m.find()){
            Object aliasValue = params.get(m.group(1));

            if (aliasValue != null && aliasValue instanceof String){
                m.appendReplacement(b, (String)aliasValue);
            } else {
                m.appendReplacement(b, gson.toJson(aliasValue));
            }
        }
        m.appendTail(b);
*/

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
