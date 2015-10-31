package com.acme.edu;

import com.jet.present.Printer;

/**
 * Средство для логирования входных данных с обработкой в режиме реального времени.
 */
public class Logger {

    //Состояние предшествующее текущему
    //str - предыдущий метод обрабатывал строку
    //sumOfIntegers - предыдущий метод обрабатывал целое число int
    //other - предыдущий метод обрабатывл любые другие данные илл Logger только начинает свою работу
    private static String previousType = "other";
    //Текущая сумма данных int
    private static int sumOfIntegers = 0;
    //Последняя залогированная строка
    private static String previousString = "";
    //Текущее количество повторов строк
    private static int sumOfStrings = 1;

    //Сброс буферов и состояний
    private static void resetCalcs(){
        sumOfIntegers = 0;
        sumOfStrings = 1;
        previousString = "";
        previousType = "other";
    }

    //Печать буфера суммы целых чисел
    private static void printBufferSum(){
        if ("sum".equals(previousType) ) {
            Printer.print("primitive: " + sumOfIntegers);
            sumOfIntegers = 0;
        }
    }

    //Печать буфера повторяюшихся строк
    private static void printBufferString(){
        if ("str".equals(previousType)){
            Printer.print("string: " + ((sumOfStrings<3) ? previousString : (previousString + " (x" + (sumOfStrings-1) + ")")));//исправить sumOfStrings
            previousString = "";
            sumOfStrings = 1;
        }
    }

    /**
     * Завершение текущего сеанса логирования.
     */
    public static void close(){
        printBufferString();
        printBufferSum();
        resetCalcs();
    }

    //Проверка переполнения типа Integer
    private static boolean catchingIntegerOverflow(Long number){
        if(number > Integer.MAX_VALUE)
            return true;
        else
            return false;
    }

    //Обработка логирования строк, согласно описанию метода log(String)
    private static void logStringSumming(String message) {
        if (!"str".equals(previousType)) {
            sumOfStrings = 1;
            previousString = message;
        } else {
            if (!previousString.equals(message))
                printBufferString();
            sumOfStrings++;
            previousString = message;
        }
    }

    /**
     * Используется для вывода переданного аргумента.
     * В случае, если метод вызывается несколько раз подряд для целых чисел, то числа суммируются и выводится их сумма.
     * Если в ходе повторных вызовов возникает переполнение, то выводится текущая сумма перед переполнением, и суммирование начинается с 0.
     * @param message сообщение для вывода
     */
    public static void log(int message) {
        printBufferString();
        if(!"sum".equals(previousType)){
            sumOfIntegers =0;
        }

        if(catchingIntegerOverflow((long) sumOfIntegers +message)){
            printBufferSum();
            resetCalcs();
            sumOfIntegers =message;
        }else{
            sumOfIntegers +=message;
        }

        previousType = "sum";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(byte message){
        printBufferString();
        printBufferSum();
        Printer.print("primitive: " + message);
        previousType = "other";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(boolean message) {
        printBufferString();
        printBufferSum();
        Printer.print("primitive: " + (message ? "true" : "false"));
        previousType = "other";
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(char message) {
        printBufferString();
        printBufferSum();
        Printer.print("char: " + message);
        previousType = "other";
    }

    /**
     * Используется для вывода переданного аргумента и подсчета повторяюшихся строчных аргументов идущих подряд.
     * @param message сообщение для вывода
     */


    public static void log(String message) {
        printBufferSum();
        logStringSumming(message);

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
        printBufferSum();
        printBufferString();
        int sum = 0;
        for(int i : message){
            sum+=i;
        }
        Printer.print("primitives: " + sum);
    }

    /**
     * Используется для вывода множества аргументов
     * @param message Множество аргументов
     */
    public static void log(int[][] message){
        printBufferString();
        printBufferSum();
        StringBuilder messageBuffer = new StringBuilder();
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

    /**
     * Используется для вывода множества строчных аргументов с подсчетом повторов подряд
     * @param message Множество аргументов
     */
    public static void log(String... message){

        printBufferSum();
        for(int i = 0; i < message.length; i++){
            /*if(i==message.length-1){
                Printer.print(message[i],false);
            }else{
                Printer.print(message[i]);
            }*/
            //printBufferString();
            logStringSumming(message[i]);
            previousType = "str";
        }
        //previousType="str";
    }

    /**
     * Используется для вывода четырехмерного массива целых чисел.
     * @param message
     */
    public static void log(int[][][][] message){
        printBufferString();
        printBufferSum();
        StringBuilder messageBuffer = new StringBuilder();
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
