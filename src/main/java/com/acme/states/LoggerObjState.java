package com.acme.states;

import com.acme.Logger;
import com.acme.decorator.LoggerDecorator;
import com.acme.exceptions.PrinterException;
import com.acme.exceptions.StateException;
import com.jet.present.Printers;

/**
 * Состояние для логирования Object
 */
public class LoggerObjState extends LoggerState {

    private Object buffer;

    /**
     * Создает экземпляр состояния для входных данных типа Object
     * @param printer Средство для вывода
     * @param decorator Средство для декорирования вывода
     */
    public LoggerObjState(Printers printer, LoggerDecorator decorator){
        super(printer,decorator);
    }

    /**
     * Печатает буфер состояния.
     */
    @Override
    public void printBuffer() throws StateException{
        try {
            printer.print(Logger.MSG + decorator.decorate("OBJ",buffer+""));
        } catch (PrinterException e) {
            throw new StateException("Cant print obj buffer",e);
        }
    }

    @Override
    public void toBuffer(Object msg){
        buffer = msg;
    }

}
