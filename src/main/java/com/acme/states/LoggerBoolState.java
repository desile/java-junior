package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Created by DeSile on 02.11.2015.
 */

/**
 * Состояние для логирования Boolean.
 */
public class LoggerBoolState extends LoggerState {

    private boolean buffer;

    /**
     * Создает экземпляр состояния для входных данных типа Boolean
     * @param printer Средство для вывода
     * @param decorator Средство для декорирования вывода
     */
    public LoggerBoolState(Printable printer, LoggerDecorator decorator){
        super(printer,decorator);
    }

    /**
     * Печатает буфер состояния.
     */
    @Override
    public void printBuffer(){
        printer.print(decorator.decorate("BOOL",(buffer ? "true" : "false")));
    }

    /**
     * Добавляем в буфер состояния входное значение.
     * @param msg аргумент, добавляемый в буфер.
     */
    @Override
    public void toBuffer(boolean msg){
        this.buffer = msg;
    }

}
