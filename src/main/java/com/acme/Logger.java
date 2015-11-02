package com.acme;


import static com.jet.present.Printer.print;
import com.acme.states.*;


/**
 * Средство для логирования входных данных с обработкой в режиме реального времени.
 */
public class Logger {

    private static LoggerState state = new LoggerState();
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
    private static int sumOfStrings = 0;
    //Максимальное количество строк, которое не декоррируется как повтор
    private static final int DECOR_NUM = 1;

    //Сброс буферов и состояний
    private static void resetLogBuffer(){
        sumOfIntegers = 0;
        sumOfStrings = 0;
        previousString = "";
        state = new LoggerState();
    }

    /**
     * Завершение текущего сеанса логирования.
     */
    public static void close(){
        state.printBuffer();
        state = new LoggerState();
    }

    //Проверка переполнения типа Integer



    /**
     * Используется для вывода переданного аргумента.
     * В случае, если метод вызывается несколько раз подряд для целых чисел, то числа суммируются и выводится их сумма.
     * Если в ходе повторных вызовов возникает переполнение, то выводится текущая сумма перед переполнением, и суммирование начинается с 0.
     * @param message сообщение для вывода
     */
    public static void log(int message) {
        if(!(state instanceof LoggerSumState)){
            state.printBuffer();
            state = new LoggerSumState();
        }
        state.toBuffer(message);

    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(boolean message) {
        state.printBuffer();
        state = new LoggerState();

        print("primitive: " + (message ? "true" : "false"));
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(char message) {
        state.printBuffer();
        state = new LoggerState();

        print("char: " + message);
    }

    /**
     * Используется для вывода переданного аргумента и подсчета повторяюшихся строчных аргументов идущих подряд.
     * @param message сообщение для вывода
     */


    public static void log(String message) {
        if(!(state instanceof LoggerStringState)){
            state.printBuffer();
            state = new LoggerStringState();
        }
        state.toBuffer(message);
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public static void log(Object message) {
        state.printBuffer();
        state = new LoggerState();

        print("reference: " + message);
    }

    /**
     * Используется для вывода суммы множества аргументов
        * @param message Множество аргументов
     */
    public static void log(int... message){
        state.printBuffer();
        int sum = 0;
        for(int i : message){
            sum+=i;
        }
        print("primitives: " + sum);
    }

    /**
     * Используется для вывода множества аргументов
     * @param message Множество аргументов
     */
    public static void log(int[][] message){
        state.printBuffer();
        StringBuilder messageBuffer = new StringBuilder();
        print("primitives matrix: {");
        for(int[] row : message){
            for(int i : row){
                messageBuffer.append(i);
                messageBuffer.append(", ");
            }
            messageBuffer.delete(messageBuffer.length() - 2, messageBuffer.length());
            print("{" + messageBuffer.toString() + "}");
            messageBuffer.setLength(0);
        }
        print("}");


    }

    /**
     * Используется для вывода множества строчных аргументов с подсчетом повторов подряд
     * @param message Множество аргументов
     */
    public static void log(String... message){

        state.printBuffer();
        state = new LoggerStringState();

        for(int i = 0; i < message.length; i++){
            state.toBuffer(message[i]);
        }
    }

    /**
     * Используется для вывода четырехмерного массива целых чисел.
     * @param message
     */
    public static void log(int[][][][] message){
        state.printBuffer();
        state = new LoggerState();

        StringBuilder messageBuffer = new StringBuilder();
        print("primitives multimatrix: {");
        for(int[][][] i3 : message){
            print("{");
            for(int[][] i2 : i3){
                print("{");
                for(int[] i1 : i2){
                    print("{");
                    for(int i : i1){
                        messageBuffer.append(i);
                        messageBuffer.append(", ");
                    }
                    messageBuffer.delete(messageBuffer.length() - 2, messageBuffer.length());
                    print(messageBuffer.toString());
                    print("}");
                }
                print("}");
            }
            print("}");
        }
        print("}");
    }

}
