package com.sti.tools.html2js;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class Main {

    public static final Pattern nameReplacePattern = Pattern.compile("(\\{\\{\\s*name\\s*\\}\\})", Pattern.DOTALL);
    public static final Pattern contentReplacePattern = Pattern.compile("(\\{\\{\\s*content\\s*\\}\\})", Pattern.DOTALL);

    public static void execute(String[] args) throws IOException, IllegalArgumentException {

        Arguments arguments = new Arguments().fulfill(args);

        String template = Tools.readFile(arguments.templateName);

        Template t = Template.parse(template);

        StringBuffer buffer = fillInTemplate(t, arguments.sourceNames);

        System.out.println(buffer.toString());
    }

    public static StringBuffer fillInTemplate(Template t, List<String> sourceNames) throws IOException{
        StringBuffer collector = new StringBuffer(t.header);
        StringBuffer buffer;
        for (String sourceName : sourceNames) {
            String html = Tools.readFile(sourceName);
            String sourceContent = formatHtmlToJs(html);

            buffer = Tools.replace(new StringBuffer(), t.body, sourceName, nameReplacePattern);
            buffer = Tools.replace(new StringBuffer(), buffer.toString(), sourceContent, contentReplacePattern);
            collector.append(buffer);
        }
        collector.append(t.footer);
        return collector;
    }

    public static String formatHtmlToJs(String html){
        String js = html;

        // todo

        return js;
    }

}
