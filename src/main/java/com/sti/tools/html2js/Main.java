package com.sti.tools.html2js;

import java.io.IOException;

public class Main {

    public static void execute(String[] args) throws IOException, IllegalArgumentException {

        Arguments arguments = new Arguments().fulfill(args);

        System.out.println(arguments);

    }

}
