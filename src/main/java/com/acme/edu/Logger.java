package com.acme.edu;

import com.jet.present.Printer;

public class Logger {

    private static String operationType = "start";//start,new,sum,strsum,finish
    private static String previousType = "start";
    private static int sum = 0;
    private static String prevString = "";
    private static int prevNumber = 0;
    private static int sumStr = 1;

    private static void resetCalcs(){
        sum = 0;
        sumStr = 1;
        prevString = "";
        operationType = "start";
        previousType = "start";
    }

    private static void printBufferSum(){
        if (previousType.equals("sum") ) {
            Printer.print("primitive: " + sum);
            sum = 0;
        }
        operationType = "new";
    }

    private static void printBufferString(){
        if (previousType.equals("str")){
            Printer.print("string: " + ((sumStr==1) ? prevString : (prevString + " (x" + (sumStr-1) + ")")));//исправить sumstr
            prevString = "";
            sumStr = 1;
        }

        operationType = "new";
    }

    public static void close(){
        //Printer.print("close: " + sum);
        printBufferString();
        printBufferSum();
        resetCalcs();
    }


    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(int message) {
        printBufferString();
        operationType = "sum";
        if(operationType.equals("sum") && !previousType.equals("sum")){
            sum=0;
        }

        if(message == Integer.MAX_VALUE){
            printBufferSum();
            Printer.print("primitive: " + Integer.MAX_VALUE);
            resetCalcs();

        }
        if(sum < Integer.MAX_VALUE)
            sum+=message;
        else{
           // printBufferSum();
        }
        previousType = "sum";
    }

    public static void log(byte message){
        printBufferString();
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
        printBufferString();
        printBufferSum();
        Printer.print("primitive: " + (message ? "true" : "false"));
        previousType = "new";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message
     */
    public static void log(char message) {
        printBufferString();
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
        if(operationType.equals("str") && !previousType.equals("str")){
            sumStr=1;
            prevString=message;
        }
        else{
            if(!prevString.equals(message))
                printBufferString();
            sumStr++;
            prevString=message;
        }
        //Printer.print("string: " + message);


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
