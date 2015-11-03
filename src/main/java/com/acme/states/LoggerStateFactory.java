package com.acme.states;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerStateFactory {

    private LoggerState boolState = new LoggerBoolState();
    private LoggerState charState = new LoggerCharState();
    private LoggerState objState = new LoggerObjState();
    private LoggerState stringState = new LoggerStringState();
    private LoggerState sumState = new LoggerSumState();
    private LoggerState comState = new LoggerState();

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
