package com.acme.exceptions;


/**
 * Класс исключений для уровня класса Logger.
 */
public class LoggerException extends Exception {

    /**
     * Конструктор исключения
     * @param msg Информационное сообщение
     * @param cause Причина возникновения
     */
    public LoggerException(String msg, Throwable cause){
        super(msg,cause);
    }

    /**
     * Конструктор исключения
     * @param msg Информационное сообщение
     */
    public LoggerException(String msg){
        super(msg);
    }

}
