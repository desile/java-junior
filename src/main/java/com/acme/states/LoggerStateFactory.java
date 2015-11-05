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

    private LoggerDecorator decorator;

    private LoggerState boolState;
    private LoggerState charState;
    private LoggerState objState;
    private LoggerState stringState;
    private LoggerState sumState;
    private LoggerState comState;

    private Map<String,String> decor;

    public LoggerStateFactory(Printable printer){

        decor = new HashMap<>();
        decorator = new LoggerPrefixDecorator(decor);

        boolState = new LoggerBoolState(printer,decorator);
        charState = new LoggerCharState(printer,decorator);
        objState = new LoggerObjState(printer,decorator);
        stringState = new LoggerStringState(printer,decorator);
        sumState = new LoggerSumState(printer,decorator);
        comState = new LoggerState(printer,decorator);
        //filling decor map
        decor.put("BOOL","primitive");
        decor.put("CHAR","char");
        decor.put("OBJ","reference");
        decor.put("STRING","string");
        decor.put("INT","primitive");
        //init decorator
    }

    public void setDecorMap(Map<String,String> decor){
        this.decor = decor;
        decorator.setDecor(decor);
    }

    public LoggerDecorator getDecorator(){
        return decorator;
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
