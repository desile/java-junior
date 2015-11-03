package com.acme.states;

import com.jet.present.Printable;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerSumState extends LoggerState {

    private int buffer;

    private boolean isIntegerOverflow(long number){
        if(number > Integer.MAX_VALUE || number < Integer.MIN_VALUE) {
            return true;
        }
        else {
            return false;
        }
    }

    public LoggerSumState(Printable printer){
        super(printer);
        buffer = 0;
    }

    @Override
    public void toBuffer(int msg){
        if(isIntegerOverflow((long)msg+buffer)){
            printBuffer();
            buffer = msg;
        }else{
            buffer+=msg;
        }
    }

    @Override
    public void printBuffer(){
        printer.print("primitive: " + buffer);
        buffer = 0;
    }


}
