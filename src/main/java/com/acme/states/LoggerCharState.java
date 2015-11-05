package com.acme.states;

import com.acme.decorator.LoggerDecorator;
import com.jet.present.Printable;


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
    public LoggerCharState(Printable printer, LoggerDecorator decorator){
        super(printer,decorator);
    }

    /**
     * Печатает буфер состояния.
     */
    @Override
    public void printBuffer() {
        printer.print(decorator.decorate("CHAR",""+buffer));
    }



    @Override
    public void toBuffer(char msg){
        this.buffer = msg;
    }
}
