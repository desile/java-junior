package com.acme.states;

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
    public void printBuffer() {
        printer.print("char: " + buffer);
    }



    @Override
    public void toBuffer(char msg){
        this.buffer = msg;
    }
}
