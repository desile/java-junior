package com.acme.states;

import com.acme.decorator.LoggerDecorator;
import com.jet.present.Printable;

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
    public LoggerObjState(Printable printer, LoggerDecorator decorator){
        super(printer,decorator);
    }

    /**
     * Печатает буфер состояния.
     */
    @Override
    public void printBuffer(){
        printer.print(decorator.decorate("OBJ",buffer+""));
    }

    @Override
    public void toBuffer(Object msg){
        buffer = msg;
    }

}
