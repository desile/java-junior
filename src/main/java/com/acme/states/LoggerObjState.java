package com.acme.states;

import static com.jet.present.Printer.print;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerObjState extends LoggerState {

    private Object buffer;

    public void printBuffer(){
        print("reference: " + buffer);
    }

    public void toBuffer(Object msg){
        buffer = msg;
    }

}
