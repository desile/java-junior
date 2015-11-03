package com.acme.states;

import static com.jet.present.Printer.print;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerBoolState extends LoggerState {

    private boolean buffer;

    @Override
    public void printBuffer(){
        print("primitive: " + (buffer ? "true" : "false"));
    }

    @Override
    public void toBuffer(boolean msg){
        this.buffer = msg;
    }

}
