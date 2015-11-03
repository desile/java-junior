package com.acme.states;

import com.jet.present.Printable;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerObjState extends LoggerState {

    private Object buffer;

    public LoggerObjState(Printable printer){
        super(printer);
    }

    public void printBuffer(){
        printer.print("reference: " + buffer);
    }

    public void toBuffer(Object msg){
        buffer = msg;
    }

}
