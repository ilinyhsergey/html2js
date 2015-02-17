package com.sti.tools.html2js;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

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

}
