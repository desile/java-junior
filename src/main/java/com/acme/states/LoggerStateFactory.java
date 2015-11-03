package com.acme.states;

import com.jet.present.Printable;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerStateFactory {

    private Printable printer;

    private LoggerState boolState;
    private LoggerState charState;
    private LoggerState objState;
    private LoggerState stringState;
    private LoggerState sumState;
    private LoggerState comState;

    public LoggerStateFactory(Printable printer){
        this.printer = printer;
        boolState = new LoggerBoolState(printer);
        charState = new LoggerCharState(printer);
        objState = new LoggerObjState(printer);
        stringState = new LoggerStringState(printer);
        sumState = new LoggerSumState(printer);
        comState = new LoggerState(printer);
    }

    public LoggerState setToBoolState(LoggerState state){
        state.printBuffer();
        return boolState;
    }
    public LoggerState setToCharState(LoggerState state){
        state.printBuffer();
        return charState;
    }

    public LoggerState setToObjState(LoggerState state){
        state.printBuffer();
        return objState;
    }

    public LoggerState setToStringState(LoggerState state){
        if(state != stringState){
            state.printBuffer();
        }
        return stringState;
    }

    public LoggerState setToSumState(LoggerState state){
        if(state != sumState){
            state.printBuffer();
        }
        return sumState;
    }

    public LoggerState setToComState(LoggerState state){
        if(state!=null){
            state.printBuffer();
        }
        return comState;
    }


}
