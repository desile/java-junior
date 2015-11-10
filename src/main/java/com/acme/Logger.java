package com.acme;


import com.acme.exceptions.LoggerException;
import com.acme.exceptions.PrinterException;
import com.acme.exceptions.StateException;
import com.acme.states.*;
import com.jet.present.Printable;
import com.jet.present.Printers;


/**
 * Средство для логирования входных данных с обработкой в режиме реального времени.
 */
public class Logger {

    private Printers printers;

    private  LoggerStateFactory lsFactory;
    private  LoggerState state;

    /**
     * Метка сообщения для строки передаваемой серверу.
     */
    public static final String MSG = "[MSG]:";
    /**
     * Метка ошибки для строки передаваемой серверу.
     */
    public static final String ERR_MSG = "[ERROR]:";


    /**
     * Инициализация логгера и первичного состояния фабрики состояний.
     * @param printerArr Коллекция принтеров, которые будут производить печать.
     */
    public Logger(Printable... printerArr) throws LoggerException {
        if(printerArr == null || printerArr.length == 0)
            throw new LoggerException("There are no printers.");
        printers = new Printers(printerArr);
        lsFactory = new LoggerStateFactory(printers);
        try {
            state = lsFactory.setToComState(null);
        } catch (StateException e) {
            throw new LoggerException("Something wrong with initializating state in factory.",e);
        }
    }

    /**
     * Завершение текущего сеанса логирования.
     */
    public  void close() throws LoggerException {
        try {
            state = lsFactory.setToComState(state);
            printers.reset();
        } catch (PrinterException | StateException e) {
            throw new LoggerException("Problem with closing log session",e);
        }
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
    public  void log(int message) throws LoggerException{
        try {
            state = lsFactory.setToSumState(state);
            state.toBuffer(message);
        }catch (StateException e){
            throw new LoggerException("Problem with logging int",e);
        }

    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public  void log(boolean message) throws LoggerException {
        try {
            state = lsFactory.setToBoolState(state);
        } catch (StateException e) {
            throw new LoggerException("Problem with logging bool",e);
        }
        state.toBuffer(message);

    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public  void log(char message) throws LoggerException{
        try {
            state = lsFactory.setToCharState(state);
        } catch (StateException e) {
            throw new LoggerException("Problem with logging char",e);
        }
        state.toBuffer(message);
    }

    /**
     * Используется для вывода переданного аргумента и подсчета повторяюшихся строчных аргументов идущих подряд.
     * @param message сообщение для вывода
     */


    public  void log(String message) throws LoggerException{
        if(message == null){
            throw new LoggerException("String is null");
        }
        if(message.isEmpty()){
            throw new LoggerException("String is empty");
        }
        try {
            state = lsFactory.setToStringState(state);
            state.toBuffer(message);
        } catch (StateException e) {
            throw new LoggerException("Problem with logging string",e);
        }

    }

    /**
     * Используется для вывода переданного аргумента.
     * @param message сообщение для вывода
     */
    public  void log(Object message) throws LoggerException {
        try {
            state = lsFactory.setToObjState(state);
        } catch (StateException e) {
            throw new LoggerException("Problem with logging object",e);
        }
        state.toBuffer(message);
    }

    /**
     * Используется для вывода суммы множества аргументов
        * @param message Множество аргументов
     */
    public  void log(int... message) throws LoggerException{
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
    public  void log(int[][] message) throws LoggerException{
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
    public  void log(String... message) throws LoggerException{
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
     * @param message Четырехмерный массив
     */
    public  void log(int[][][][] message) throws LoggerException{
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
