package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerBoolState extends LoggerState {

    private boolean buffer;

    public LoggerBoolState(Printable printer){
        super(printer);
    }

    @Override
    public void printBuffer(LoggerDecorator decorator){
        printer.print(decorator.decorate("BOOL",(buffer ? "true" : "false")));
    }

    @Override
    public void toBuffer(boolean msg){
        this.buffer = msg;
    }

}
