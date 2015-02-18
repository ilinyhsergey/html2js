package com.sti.tools.html2js;

public class App {
    public static String help = "\tsyntax: \"html2js -t template.js -o output.js src1.html [src2.html ...]\"\n" +
            "\tformat of template file is: \"{{for}} {{name}} {{content}} {{end}} [ {{for}}...{{end}} ]\"\n" +
            "\tbody between {{for}} and {{end}} tags will be repeat for each source file\n" +
            "\ttags {{name}} and {{content}} will be replaced to file name and file content for each source file\n";

    /**
     * @param args - format 'html2js -t template.js -o output.js src1.html [src2.html ...]'
     */
    public static void main(String[] args) {
        try {
            if (args.length > 0 && args[0].compareToIgnoreCase("help") == 0) {
                System.out.println(help);
                System.exit(0);
            }
            Main.execute(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
