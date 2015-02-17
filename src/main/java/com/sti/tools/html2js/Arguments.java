package com.sti.tools.html2js;

import java.util.ArrayList;
import java.util.List;


public class Arguments {
    public String templateName = null;
    public String outputName = null;
    public List<String> sourceNames = new ArrayList<String>();

    Arguments fulfill(String[] args) {
        if (args.length < 5)
            throw new IllegalArgumentException("Wrong format. Must be: 'html2js -t template.js -o output.js src1.html [src2.html ...]'");

        for (int i = 0; i < args.length; ++i) {
            String arg = args[i];

            if (Tools.isEmptyOrNull(arg))
                throw new IllegalArgumentException("Some arguments is empty.");

            if (templateName == null && "-t".equalsIgnoreCase(arg)) {
                templateName = args[++i];
            } else if (outputName == null && "-o".equalsIgnoreCase(arg)) {
                outputName = args[++i];
            } else {
                sourceNames.add(arg);
            }
        }
        return this;
    }


    @Override
    public String toString() {
        String srcNames = null;

        for (String n: sourceNames) {
            if (!Tools.isEmptyOrNull(srcNames))
                srcNames += ", ";
            srcNames += n;
        }

        return "Arguments{" +
                "templateName='" + templateName + '\'' +
                ", outputName='" + outputName + '\'' +
                ", sourceNames=[" + srcNames +
                "]}";
    }
}
