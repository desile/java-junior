package com.acme.edu;

public class Logger {

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(int message) {
        print("primitive: " + message);
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(boolean message) { print("primitive: " + (message ? "true" : "false"));}

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(char message) {print("char: " + message);}

    private static void print(String s){
        System.out.println(s);
    }
}
