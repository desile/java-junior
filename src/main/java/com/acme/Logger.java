package com.acme;


import com.acme.states.*;
import com.jet.present.ConsolePrinter;
import com.jet.present.Printable;


/**
 * Средство для логирования входных данных с обработкой в режиме реального времени.
 */
public class Logger {

    private  Printable printer = new ConsolePrinter();

    private  LoggerStateFactory lsFactory = new LoggerStateFactory(printer);
    private  LoggerState state;

     {
        state = lsFactory.setToComState(state);
    }


    //Сброс буферов и состояний

    /**
     * Завершение текущего сеанса логирования.
     */
    public  void close(){
        state = lsFactory.setToComState(state);
    }

    //Проверка переполнения типа Integer



    /**
     * Используется для вывода переданного аргумента.
     * В случае, если метод вызывается несколько раз подряд для целых чисел, то числа суммируются и выводится их сумма.
     * Если в ходе повторных вызовов возникает переполнение, то выводится текущая сумма перед переполнением, и суммирование начинается с 0.
     * @param message сообщение для вывода
     */
    public  void log(int message) {
        state = lsFactory.setToSumState(state);
        state.toBuffer(message);

    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public  void log(boolean message) {
        state = lsFactory.setToBoolState(state);
        state.toBuffer(message);

    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public  void log(char message) {
        state = lsFactory.setToCharState(state);
        state.toBuffer(message);

    }

    /**
     * Используется для вывода переданного аргумента и подсчета повторяюшихся строчных аргументов идущих подряд.
     * @param message сообщение для вывода
     */


    public  void log(String message) {
        state = lsFactory.setToStringState(state);
        state.toBuffer(message);
    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public  void log(Object message) {
        state = lsFactory.setToObjState(state);
        state.toBuffer(message);
    }

    /**
     * Используется для вывода суммы множества аргументов
        * @param message Множество аргументов
     */
    public  void log(int... message){
        state.printBuffer();
        int sum = 0;
        for(int i : message){
            sum+=i;
        }
        printer.print("primitives: " + sum);
    }

    /**
     * Используется для вывода множества аргументов
     * @param message Множество аргументов
     */
    public  void log(int[][] message){
        state.printBuffer();
        StringBuilder messageBuffer = new StringBuilder();
        printer.print("primitives matrix: {");
        for(int[] row : message){
            for(int i : row){
                messageBuffer.append(i);
                messageBuffer.append(", ");
            }
            messageBuffer.delete(messageBuffer.length() - 2, messageBuffer.length());
            printer.print("{" + messageBuffer.toString() + "}");
            messageBuffer.setLength(0);
        }
        printer.print("}");


    }

    /**
     * Используется для вывода множества строчных аргументов с подсчетом повторов подряд
     * @param message Множество аргументов
     */
    public  void log(String... message){

        state.printBuffer();
        state = new LoggerStringState(printer);

        for(int i = 0; i < message.length; i++){
            state.toBuffer(message[i]);
        }
    }

    /**
     * Используется для вывода четырехмерного массива целых чисел.
     * @param message
     */
    public  void log(int[][][][] message){
        state.printBuffer();
        state = new LoggerState(printer);

        StringBuilder messageBuffer = new StringBuilder();
        printer.print("primitives multimatrix: {");
        for(int[][][] i3 : message){
            printer.print("{");
            for(int[][] i2 : i3){
                printer.print("{");
                for(int[] i1 : i2){
                    printer.print("{");
                    for(int i : i1){
                        messageBuffer.append(i);
                        messageBuffer.append(", ");
                    }
                    messageBuffer.delete(messageBuffer.length() - 2, messageBuffer.length());
                    printer.print(messageBuffer.toString());
                    printer.print("}");
                }
                printer.print("}");
            }
            printer.print("}");
        }
        printer.print("}");
    }

}