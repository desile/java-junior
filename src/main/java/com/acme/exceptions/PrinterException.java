package com.acme.exceptions;

/**
 * Класс исключений для уровня семейста классов Printable
 */
public class PrinterException extends Exception {

    /**
     * Конструктор исключения
     * @param msg Информационное сообщение
     * @param cause Причина возникновения
     */
    public PrinterException(String msg, Throwable cause){
        super(msg,cause);
    }

    /**
     * Конструктор исключения
     * @param msg Информационное сообщение
     */
    public PrinterException(String msg){
        super(msg);
    }

}
