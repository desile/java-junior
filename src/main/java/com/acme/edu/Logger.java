package com.acme.edu;

import com.jet.present.Printer;

public class Logger {

    private static String operationType = "start";//start,new,sum,strsum,finish
    private static String previousType = "start";
    private static int sum = 0;
    private static String prevString = "";
    private static int prevNumber = 0;
    private static int sumStr = 0;

    private static void resetCalcs(){
        sum = 0;
        sumStr = 0;
        prevString = "";
        operationType = "start";
        previousType = "start";
    }

    private static void printBufferSum(){
        if (previousType.equals("sum")) {
            Printer.print("primitive: " + sum);
            sum = 0;
        }
        operationType = "new";
    }

    public static void close(){
        //Printer.print("close: " + sum);
        printBufferSum();
        resetCalcs();
    }


    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(int message) {
        operationType = "sum";
        if(operationType.equals("sum") && !previousType.equals("sum")){
            sum=0;
        }
        sum+=message;
        previousType = "sum";
    }

    public static void log(byte message){
        printBufferSum();
        operationType = "new";
        Printer.print("primitive: " + message);
        previousType = "new";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(boolean message) {
        printBufferSum();
        Printer.print("primitive: " + (message ? "true" : "false"));
        previousType = "new";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(char message) {

        printBufferSum();
        Printer.print("char: " + message);
        previousType = "new";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(String message) {
        printBufferSum();
        operationType = "str";
        Printer.print("string: " + message);



        previousType = "str";
    }

    /**
     * Используется для вывода переданного аргумента
     * @param message
     */
    public static void log(Object message) {
        printBufferSum();
        Printer.print("reference: " + message);
    }



}
