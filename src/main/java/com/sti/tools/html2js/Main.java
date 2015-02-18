package com.sti.tools.html2js;

import java.io.*;
import java.util.Map;

public class Main {


    public static void execute(String[] args) throws IOException, IllegalArgumentException {
        // get arguments from command lile
        Arguments arguments = new Arguments().fulfill(args);
        // read content fo template file
        String templateFile = Tools.readFile(arguments.templateName);
        // get content from multiple source files
        Map<String, String> sourceContentMap = Tools.prepareSourceContent(arguments.sourceNames);
        // place content in a template
        StringBuffer collector = Tools.placeContentInTemplate(templateFile, sourceContentMap);
        // write result to output file
        Tools.writeFile(arguments.outputName, collector.toString());
    }

}
