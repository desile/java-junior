package com.acme.exceptions;

/**
 * Класс-исключение для уровня классов состояний.
 */
public class StateException extends Exception {

    /**
     * Конструктор исключения
     * @param msg Информационное сообщение
     * @param cause Причина возникновения
     */
    public StateException(String msg, Throwable cause){
        super(msg,cause);
    }

    /**
     * Конструктор исключения
     * @param msg Информационное сообщение
     */
    public StateException(String msg){
        super(msg);
    }

}
