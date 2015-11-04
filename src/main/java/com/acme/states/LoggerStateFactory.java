package com.acme.states;

import com.acme.LoggerDecorator;
import com.acme.LoggerPrefixDecorator;
import com.jet.present.Printable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerStateFactory {

    private Printable printer;
    private LoggerDecorator decorator;

    private LoggerState boolState;
    private LoggerState charState;
    private LoggerState objState;
    private LoggerState stringState;
    private LoggerState sumState;
    private LoggerState comState;

    private HashMap<String,String> decor;

    public LoggerStateFactory(Printable printer){
        this.printer = printer;
        boolState = new LoggerBoolState(printer);
        charState = new LoggerCharState(printer);
        objState = new LoggerObjState(printer);
        stringState = new LoggerStringState(printer);
        sumState = new LoggerSumState(printer);
        comState = new LoggerState(printer);
        decor = new HashMap<>();
        //filling decor map
        decor.put("BOOL","primitive");
        decor.put("CHAR","char");
        decor.put("OBJ","reference");
        decor.put("STRING","string");
        decor.put("INT","primitive");
        //init decorator
        decorator = new LoggerPrefixDecorator(decor);
    }

    public void setDecorMap(HashMap<String,String> decor){
        this.decor = decor;
        decorator.setDecor(decor);
    }

    public LoggerDecorator getDecorator(){
        return decorator;
    }

    public LoggerState setToBoolState(LoggerState state){
        state.printBuffer(decorator);
        return boolState;
    }
    public LoggerState setToCharState(LoggerState state){
        state.printBuffer(decorator);
        return charState;
    }

    public LoggerState setToObjState(LoggerState state){
        state.printBuffer(decorator);
        return objState;
    }

    public LoggerState setToStringState(LoggerState state){
        //printer.print("String");
        if(state != stringState || state.flushBuffer()){
            //printer.print("flushed");
            state.printBuffer(decorator);
        }
        return stringState;
    }

    public LoggerState setToSumState(LoggerState state){
        if(state != sumState || state.flushBuffer()){
            state.printBuffer(decorator);
        }
        return sumState;
    }

    public LoggerState setToComState(LoggerState state){
        //printer.print("asshole");
        if(state!=null){
            if(state.flushBuffer())
                state.printBuffer(decorator);
            state.printBuffer(decorator);
        }
        return comState;
    }


}
