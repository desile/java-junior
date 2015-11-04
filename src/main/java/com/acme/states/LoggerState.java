package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerState {

    protected Printable printer;

    public LoggerState(Printable printer){
        this.printer = printer;
    }

    public void toBuffer(String stringBuffer) {
        //throw new OperationNotSupportedException();
    }

    public void toBuffer(int num) {
        //throw new OperationNotSupportedException();
    }

    public void toBuffer(char msg){

    }

    public void toBuffer(boolean msg){

    }

    public void toBuffer(Object ref){

    }

    public void printBuffer(LoggerDecorator decorator){

    }

    public boolean flushBuffer(){
        return false;
    }


}
