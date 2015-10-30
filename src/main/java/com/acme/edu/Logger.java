package com.acme.edu;

import com.jet.present.Printer;

public class Logger {

    private static String operationType = "new";//start,new,sum,strsum
    private static String previousType = "new";
    private static int sum = 0;
    private static String prevString = "";
    private static int sumStr = 1;

    private static void resetCalcs(){
        sum = 0;
        sumStr = 1;
        prevString = "";
        operationType = "new";
        previousType = "new";
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

    /**
     * Завершение текущего сеанса логирования.
     */
    public static void close(){
        //Printer.print("close: " + sum);
        printBufferString();
        printBufferSum();
        resetCalcs();
    }


    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(int message) {
        printBufferString();
        if(!previousType.equals("sum")){
            sum=0;
        }

        if(message == Integer.MAX_VALUE){
            printBufferSum();
            resetCalcs();

        }
        if(sum < Integer.MAX_VALUE)
            sum+=message;

        previousType = "sum";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(byte message){
        printBufferString();
        printBufferSum();
        operationType = "new";
        Printer.print("primitive: " + message);
        previousType = "new";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(boolean message) {
        printBufferString();
        printBufferSum();
        Printer.print("primitive: " + (message ? "true" : "false"));
        previousType = "new";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(char message) {
        printBufferString();
        printBufferSum();
        Printer.print("char: " + message);
        previousType = "new";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(String message) {
        printBufferSum();
        if(!previousType.equals("str")){
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
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(Object message) {
        printBufferSum();
        Printer.print("reference: " + message);
    }

    /**
     * Используется для вывода суммы множества аргументов
     * @param message Множество аргументов
     */
    public static void log(int... message){
        //StringBuffer messageBuffer = new StringBuffer();
        int sum = 0;
        for(int i : message){
            sum+=i;
        }
        //messageBuffer.delete(messageBuffer.length() - 2, messageBuffer.length());
        //Printer.print("primitives : {" + messageBuffer + "}");
        Printer.print("primitives: " + sum);
    }

    /**
     * Используется для вывода множества аргументов
     * @param message Множество аргументов
     */
    public static void log(int[][] message){
        StringBuffer messageBuffer = new StringBuffer();
        Printer.print("primitives matrix: {");
        for(int[] row : message){
            for(int i : row){
                messageBuffer.append(i);
                messageBuffer.append(", ");
            }
            messageBuffer.delete(messageBuffer.length() - 2, messageBuffer.length());
            Printer.print("{" + messageBuffer.toString() + "}");
            messageBuffer.setLength(0);
        }
        Printer.print("}");


    }

    public static void log(String... message){
        for(int i = 0; i < message.length; i++){
            if(i==message.length-1){
                Printer.print(message[i],false);
            }else{
                Printer.print(message[i]);
            }
        }
    }

    public static void log(int[][][][] message){
        StringBuffer messageBuffer = new StringBuffer();
        Printer.print("primitives multimatrix: {");
        for(int[][][] i3 : message){
            Printer.print("{");
            for(int[][] i2 : i3){
                Printer.print("{");
                for(int[] i1 : i2){
                    Printer.print("{");
                    for(int i : i1){
                        messageBuffer.append(i);
                        messageBuffer.append(", ");
                    }
                    messageBuffer.delete(messageBuffer.length() - 2, messageBuffer.length());
                    Printer.print(messageBuffer.toString());
                    Printer.print("}");
                }
                Printer.print("}");
            }
            Printer.print("}");
        }
        Printer.print("}");
    }

}
