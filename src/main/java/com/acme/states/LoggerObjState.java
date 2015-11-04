package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerObjState extends LoggerState {

    private Object buffer;

    public LoggerObjState(Printable printer, LoggerDecorator decorator){
        super(printer,decorator);
    }

    public void printBuffer(){
        printer.print(decorator.decorate("OBJ",buffer+""));
    }

    public void toBuffer(Object msg){
        buffer = msg;
    }

}
