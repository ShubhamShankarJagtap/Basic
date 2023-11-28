package com.example;

import java.io.FileInputStream;
import java.io.IOException;

public class Basic {

    String path = ".//Feature//Demo.Feature";
    FileInputStream inputStream;

    void run() {
        System.out.println(" I am running");
    }

    void run(int a) {
        System.out.println(" i am running at a speend of " + a + "MPH");
    }

    void run(String s) throws IOException {
        inputStream = new FileInputStream(path);
        int character = inputStream.read();

        while (character != -1) {

            char c = (char) character;
            System.out.println(c);
            inputStream.close();
        }
        System.out.println(s);
    }

    public static void main(String[] args) throws IOException {
        Basic foo = new Basic();
        foo.run();
        foo.run(10);
        foo.run(" This is the String representation of run.");
    }
}
