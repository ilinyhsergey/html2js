package com.sti.tools.html2js;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {
    public static final Pattern nameReplacePattern = Pattern.compile("(\\{\\{\\s*name\\s*\\}\\})", Pattern.DOTALL);
    public static final Pattern contentReplacePattern = Pattern.compile("(\\{\\{\\s*content\\s*\\}\\})", Pattern.DOTALL);
    public static final Pattern escapeReplacePattern = Pattern.compile("(\")");
    public static final Pattern repeatTmplatePattern = Pattern.compile("\\{\\{\\s*for\\s*\\}\\}(.*?)\\{\\{\\s*end\\s*\\}\\}", Pattern.DOTALL);

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
    public static void writeFile(String outputName, String content) throws IOException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(new File(outputName)));
            writer.write(content, 0, content.length());
//            writer.newLine();
        } finally {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        }
    }

    public static StringBuffer replace(StringBuffer buffer, String template, String replaceContent, Pattern pattern) {
        Matcher m = pattern.matcher(template);
        while (m.find()) {
            m.appendReplacement(buffer, Matcher.quoteReplacement(replaceContent));
        }
        m.appendTail(buffer);
        return buffer;
    }

    public static Map<String, String> prepareSourceContent(List<String> sourceNames) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        for (String sourceName : sourceNames) {
            String html = Tools.readFile(sourceName);
            String sourceContent = formatHtmlToJs(html);
            map.put(sourceName, sourceContent);
        }
        return map;
    }

    public static String processTemplateBody(String templateBody, Map<String, String> sourceContentMap) {
        StringBuffer buffer;
        StringBuilder collector = new StringBuilder();

        for (String sourceName : sourceContentMap.keySet()) {
            String sourceContent = sourceContentMap.get(sourceName);
            buffer = Tools.replace(new StringBuffer(), templateBody, sourceName, nameReplacePattern);
            buffer = Tools.replace(new StringBuffer(), buffer.toString(), sourceContent, contentReplacePattern);
            collector.append(buffer);
        }
        return collector.toString();
    }

    public static String formatHtmlToJs(String html) {
        String replacedHtml = Tools.replace(new StringBuffer(), html, "\\\"", escapeReplacePattern).toString();

        String[] lines = replacedHtml.split("\n");
        StringBuilder builder = new StringBuilder();

        for (String line : lines) {
            if (builder.length() > 0)
                builder.append(" +\n");
            builder.append("\"").append(line).append("\"");
        }

        return builder.toString();
    }

    public static StringBuffer placeContentInTemplate(String template, Map<String, String> contentMap) {
        StringBuffer collector = new StringBuffer();
        Matcher m = repeatTmplatePattern.matcher(template);
        while (m.find()) {
            String body = m.group(1);
            String processedBody = Tools.processTemplateBody(body, contentMap);
            m.appendReplacement(collector, Matcher.quoteReplacement(processedBody));
        }
        m.appendTail(collector);
        return collector;
    }

}
