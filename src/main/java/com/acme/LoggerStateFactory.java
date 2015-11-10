package com.acme;

import com.acme.decorator.LoggerDecorator;
import com.acme.decorator.LoggerPrefixDecorator;
import com.acme.exceptions.StateException;
import com.acme.states.*;
import com.jet.present.Printers;

import java.util.HashMap;
import java.util.Map;

/**
 * Фабрика состояний.<br>
 * Отвечает за возвращение корректных состояний и печати/очистки буферов.
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

    /**
     * Конструктор фабрики инстанцирует объекты всех состояний и создает декоратор.
     * @param printers Средство вывода
     */
    public LoggerStateFactory(Printers printers){

        decor = new HashMap<>();
        decorator = new LoggerPrefixDecorator(decor);

        boolState = new LoggerBoolState(printers,decorator);
        charState = new LoggerCharState(printers,decorator);
        objState = new LoggerObjState(printers,decorator);
        stringState = new LoggerStringState(printers,decorator);
        sumState = new LoggerSumState(printers,decorator);
        comState = new LoggerState(printers,decorator);
        //filling decor map
        decor.put("BOOL","primitive");
        decor.put("CHAR","char");
        decor.put("OBJ","reference");
        decor.put("STRING","string");
        decor.put("INT","primitive");
        //init decorator
    }

    /**
     * Установить в фабрику свой словарь декораций
     * @param decor Словарь декораций.
     */
    public void setDecorMap(Map<String,String> decor){
        this.decor = decor;
        decorator.setDecor(decor);
    }

    /**
     * Получить текущий декоратор фабрики.
     * @return Декоратор фабрики.
     */
    public LoggerDecorator getDecorator(){
        return decorator;
    }

    /**
     * Напечатать буфер состояния и переключить состояние в Bool
     * @param state текущее состояние
     * @return состояние Bool
     */
    public LoggerState setToBoolState(LoggerState state) throws StateException{
        state.printBuffer();
        return boolState;
    }

    /**
     * Напечатать буфер состояния и переключить состояние в Char
     * @param state текущее состояние
     * @return состояние Char
     */
    public LoggerState setToCharState(LoggerState state) throws StateException{
        state.printBuffer();
        return charState;
    }

    /**
     * Напечатать буфер состояния и переключить состояние в Object
     * @param state текущее состояние
     * @return состояние Object
     */
    public LoggerState setToObjState(LoggerState state) throws StateException{
        state.printBuffer();
        return objState;
    }

    /**
            * Напечатать буфер состояния, если передаваемое состояние не такое же (не String) и переключить состояние в String
    * @param state текущее состояние
    * @return состояние String
    */
    public LoggerState setToStringState(LoggerState state) throws StateException{
        if(state != stringState){
            state.printBuffer();
        }
        return stringState;
    }

    /**
     * Напечатать буфер состояния, если передаваемое состояние не такое же (не Sum) и переключить состояние в Sum
     * @param state текущее состояние
     * @return состояние Sum
     */
    public LoggerState setToSumState(LoggerState state) throws StateException{
        if(state != sumState){
            state.printBuffer();
        }
        return sumState;
    }

    /**
     * Напечатать буфер состояния, если передаваемое состояние существует (не null) и переключить в общее состояние
     * @param state текущее состояние
     * @return общее состояние
     */
    public LoggerState setToComState(LoggerState state) throws StateException{
        if(state!=null){
            state.printBuffer();
        }
        return comState;
    }


}
