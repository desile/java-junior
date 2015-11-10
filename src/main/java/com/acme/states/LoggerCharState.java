package com.acme.states;

import com.acme.Logger;
import com.acme.decorator.LoggerDecorator;
import com.acme.exceptions.PrinterException;
import com.acme.exceptions.StateException;
import com.jet.present.Printers;


/**
 * Состояние для логирования Char
 */
public class LoggerCharState extends LoggerState {

    private char buffer;

/**
 * Создает экземпляр состояния для входных данных типа Char
 * @param printer Средство для вывода
 * @param decorator Средство для декорирования вывода
*/
    public LoggerCharState(Printers printer, LoggerDecorator decorator){
        super(printer,decorator);
    }

    /**
     * Печатает буфер состояния.
     */
    @Override
    public void printBuffer() throws StateException {
        try {
            printer.print(Logger.MSG + decorator.decorate("CHAR",""+buffer));
        } catch (PrinterException e) {
            throw new StateException("Cant print char buffer",e);
        }
    }



    @Override
    public void toBuffer(char msg){
        this.buffer = msg;
    }
}
