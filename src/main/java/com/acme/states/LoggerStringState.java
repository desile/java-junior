package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerStringState extends LoggerState {

    private String buffer;
    private String newBuffer;
    private int numOfBuffer;

    private boolean fBuffer = false;

    public LoggerStringState(Printable printer){
        super(printer);
        buffer = "";
        newBuffer = "";
        numOfBuffer = 0;
    }

    public boolean flushBuffer(){
        return fBuffer;
    }


    @Override
    public void toBuffer(String msg){
        if(buffer.isEmpty()) {
            buffer = msg;
            numOfBuffer = 1;
        }
        else{
            if(buffer.equals(msg)){
                numOfBuffer++;
            }
            else{
                //printBuffer();
                fBuffer = true;
                newBuffer = msg;
                numOfBuffer = 1;
            }
        }
    }

    @Override
    public void printBuffer(LoggerDecorator decorator){
        printer.print(decorator.decorate("STRING",((numOfBuffer < 2) ? buffer : buffer + " (x" + numOfBuffer + ")")));
        if(fBuffer){
            buffer = newBuffer;
            numOfBuffer = 1;
            newBuffer = "";
            fBuffer = false;
        }
        else {
            buffer = "";
            numOfBuffer = 0;
        }
    }

}
