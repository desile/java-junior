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
    private  LoggerState state = lsFactory.setToComState(null);


    //Сброс буферов и состояний

    /**
     * Завершение текущего сеанса логирования.
     */
    public  void close(){
        state = lsFactory.setToComState(state);
    }

    public LoggerStateFactory getStateFactory(){
        return lsFactory;
    }

    public void setStateFactory(LoggerStateFactory lsf){
        this.lsFactory = lsf;
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


    public  void log(String message){
        if(message == null){
            throw new NullPointerException("String is null");
        }
        if(message.isEmpty()){
            throw new IllegalArgumentException("String is empty");
        }
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
        if(message == null){
            throw new NullPointerException("Array is null");
        }
        if(message.length == 0){
            throw new IllegalArgumentException("Empty array");
        }
        for(int i : message){
            log(i);
        }
    }

    /**
     * Используется для вывода множества аргументов
     * @param message Множество аргументов
     */
    public  void log(int[][] message){
        if(message == null){
            throw new NullPointerException("Array is null");
        }
        if(message.length == 0){
            throw new IllegalArgumentException("Empty array");
        }
        for(int[] i : message){
            log(i);
        }

    }

    /**
     * Используется для вывода множества строчных аргументов с подсчетом повторов подряд
     * @param message Множество аргументов
     */
    public  void log(String... message){
        if(message == null){
            throw new NullPointerException("Array is null");
        }
        if(message.length == 0){
            throw new IllegalArgumentException("Empty array");
        }
        for(String s : message){
            log(s);
        }
    }

    /**
     * Используется для вывода четырехмерного массива целых чисел.
     * @param message
     */
    public  void log(int[][][][] message){
        if(message == null){
            throw new NullPointerException("Array is null");
        }
        if(message.length == 0){
            throw new IllegalArgumentException("Empty array");
        }

        for(int[][][] i3 : message){
            if(i3 == null){
                throw new NullPointerException("Some inner arrays is null");
            }
            for(int[][] i2: i3){
                log(i2);
            }
        }
    }

}
