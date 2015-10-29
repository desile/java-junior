package com.acme.edu;

public class Logger {
    public static void log(int message) {
        print("primitive: " + message);
    }

    public static void log(byte message) {
        print("primitive: " + message);
    }

    public static void log(boolean message) { print("primitive: " + (message ? "true" : "false"));}

    public static void log(char message) {print("char: " + message);}

    private static void print(String s){
        System.out.println(s);
    }
}
