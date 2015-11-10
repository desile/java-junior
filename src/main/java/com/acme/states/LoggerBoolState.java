package com.acme.states;

import com.acme.Logger;
import com.acme.decorator.LoggerDecorator;
import com.acme.exceptions.PrinterException;
import com.acme.exceptions.StateException;
import com.jet.present.Printers;

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
    public LoggerBoolState(Printers printer, LoggerDecorator decorator){
        super(printer,decorator);
    }

    /**
     * Печатает буфер состояния.
     */
    @Override
    public void printBuffer() throws StateException{
        try {
            printer.print(Logger.MSG + decorator.decorate("BOOL",buffer ? "true" : "false"));
        } catch (PrinterException e) {
            throw new StateException("Cant print bool buffer", e);
        }
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
