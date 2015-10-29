package com.acme.edu;

import com.jet.present.Printer;

public class Logger {

    private Logger(){

    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(int message) {
        Printer.print("primitive: " + message);
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(boolean message) { Printer.print("primitive: " + (message ? "true" : "false"));}

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(char message) {Printer.print("char: " + message);}

}
