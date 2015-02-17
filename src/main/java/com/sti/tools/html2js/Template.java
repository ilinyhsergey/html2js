package com.sti.tools.html2js;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {
    public String header;
    public String body;
    public String footer;

    public static final Pattern repeatTmplatePattern = Pattern.compile("\\{\\{\\s*for\\s*\\}\\}(.*)\\{\\{\\s*end\\s*\\}\\}", Pattern.DOTALL);
    public static final Pattern headerTmplatePattern = Pattern.compile("^(.*)\\{\\{\\s*for\\s*\\}\\}", Pattern.DOTALL);
    public static final Pattern footerTmplatePattern = Pattern.compile("\\{\\{\\s*end\\s*\\}\\}(.*)$", Pattern.DOTALL);

    public static Template parse(String template){
        Template result = new Template();

        Matcher repeatTmplateMatcher = repeatTmplatePattern.matcher(template);
        if (repeatTmplateMatcher.find()){
            result.body = repeatTmplateMatcher.group(1);
        }

        Matcher headerTmplateMatcher = headerTmplatePattern.matcher(template);
        if (headerTmplateMatcher.find()){
            result.header = headerTmplateMatcher.group(1);
        }

        Matcher footerTmplateMatcher = footerTmplatePattern.matcher(template);
        if (footerTmplateMatcher.find()){
            result.footer = footerTmplateMatcher.group(1);
        }

        return result;
    }

    @Override
    public String toString() {
        return "Template{" +
                "header='" + header + '\'' +
                ", body='" + body + '\'' +
                ", footer='" + footer + '\'' +
                '}';
    }
}
