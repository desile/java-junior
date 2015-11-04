package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerStringState extends LoggerState {

    private String buffer;
    private int numOfBuffer;

    public LoggerStringState(Printable printer, LoggerDecorator decorator){
        super(printer,decorator);
        buffer = "";
        numOfBuffer = 0;
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
                printBuffer();
                buffer = msg;
                numOfBuffer = 1;
            }
        }
    }

    @Override
    public void printBuffer(){
        printer.print(decorator.decorate("STRING",((numOfBuffer < 2) ? buffer : buffer + " (x" + numOfBuffer + ")")));
            buffer = "";
            numOfBuffer = 0;

    }

}
