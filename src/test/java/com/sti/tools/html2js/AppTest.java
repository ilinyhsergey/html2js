package com.sti.tools.html2js;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    public void testArguments() {
        Arguments fulfill;

        fulfill = new Arguments().fulfill(new String[]{"-t", "template.js", "-o", "output.js", "src1.html", "src2.html"});
        assertEquals("output.js", fulfill.outputName);
        assertEquals("template.js", fulfill.templateName);
        assertEquals("src1.html", fulfill.sourceNames.get(0));
        assertEquals("src2.html", fulfill.sourceNames.get(1));


        fulfill = new Arguments().fulfill(new String[]{"src0.html", "-t", "template.js", "src1.html", "-o", "output.js", "src2.html"});
        assertEquals("output.js", fulfill.outputName);
        assertEquals("template.js", fulfill.templateName);
        assertEquals("src0.html", fulfill.sourceNames.get(0));
        assertEquals("src1.html", fulfill.sourceNames.get(1));
        assertEquals("src2.html", fulfill.sourceNames.get(2));

        fulfill = new Arguments().fulfill(new String[]{"src0.html", "src1.html", "-t", "template.js", "-o", "output.js"});
        assertEquals("output.js", fulfill.outputName);
        assertEquals("template.js", fulfill.templateName);
        assertEquals("src0.html", fulfill.sourceNames.get(0));
        assertEquals("src1.html", fulfill.sourceNames.get(1));


    }

    public void testReplace() {
        String censored = Tools.replace("$1<a>\\<b>foo</b>\\</a>$2\\<b>bar</b>\\$3$4$5$6$7", "censored", Pattern.compile("<b>(.*?)</b>")).toString();
        assertEquals("$1<a>\\censored\\</a>$2\\censored\\$3$4$5$6$7", censored);
    }

    public void testFormatHtmlToJs() {
        String js = Tools.formatHtmlToJs("<div></div>\n<a></a>");
        assertEquals("\"<div></div>\" +\n\"<a></a>\"", js);

        js = Tools.formatHtmlToJs("<textarea word-validation-regexp=\"^[\\S]{0,}$\">\n</textarea>");
        assertEquals("\"<textarea word-validation-regexp=\\\"^[\\\\S]{0,}$\\\">\" +\n\"</textarea>\"", js);
    }

    public void testProcessTemplateBody() {
        HashMap<String, String> contentMap = new HashMap<String, String>(){{
            put("src1.html", "foo");
            put("src2.html", "bar");
        }};
        String template = "var name = '{{\n   name \t}}', content = {{ content\n}}\n";
        assertEquals("var name = 'src2.html', content = bar\n" +
                "var name = 'src1.html', content = foo\n", Tools.processTemplateBody(template, contentMap));
    }

    public void testPlaceContentInTemplate() {
        HashMap<String, String> contentMap = new HashMap<String, String>(){{
            put("src1.html", "\"<div ng-class=\\\"{main:isMain}\\\">file 1 $3</div>\"");
            put("src2.html", "\"<body ng-show='false'>file 2</body>\"");
        }};
        String template = "// js header\n" +
                "{{ for}}\n" +
                "    var fileName = '{{name }}';\n" +
                "    var filecontent = {{ content }};\n" +
                "{{\n" +
                "    end\n" +
                "}}\n" +
                "var names = [];\n" +
                "{{for}}names.push('{{name}}');{{end}}\n" +
                "console.log(names);\n" +
                "// js footer";
        String res = Tools.placeContentInTemplate(template, contentMap).toString();

        assertEquals("// js header\n" +
                "\n" +
                "    var fileName = 'src2.html';\n" +
                "    var filecontent = \"<body ng-show='false'>file 2</body>\";\n" +
                "\n" +
                "    var fileName = 'src1.html';\n" +
                "    var filecontent = \"<div ng-class=\\\"{main:isMain}\\\">file 1 $3</div>\";\n" +
                "\n" +
                "var names = [];\n" +
                "names.push('src2.html');names.push('src1.html');\n" +
                "console.log(names);\n" +
                "// js footer", res);
    }

}
