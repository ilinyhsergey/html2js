package com.sti.tools.html2js;

import java.io.IOException;

public class App {
    /**
     * @param args - format 'html2js -t template.js -o output.js src1.html [src2.html ...]'
     */
    public static void main(String[] args) {
        try {
            Main.execute(args);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
