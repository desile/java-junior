package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;


/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerCharState extends LoggerState {

    private char buffer;

    public LoggerCharState(Printable printer){
        super(printer);
    }

    @Override
    public void printBuffer(LoggerDecorator decorator) {
        printer.print(decorator.decorate("CHAR",""+buffer));
    }



    @Override
    public void toBuffer(char msg){
        this.buffer = msg;
    }
}
