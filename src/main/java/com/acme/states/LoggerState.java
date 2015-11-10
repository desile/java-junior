package com.acme.states;

import com.acme.decorator.LoggerDecorator;
import com.acme.exceptions.StateException;
import com.jet.present.Printers;

/**
 * Базовый класс состояния, который ирает роль как родителя всех специфических состояний, так и заглушки для ситуаций,<br>
 * когда состояние нам требуется, но мы не ожидаем от него никакого поведения.
 */
public class LoggerState {

    protected Printers printer;
    protected LoggerDecorator decorator;

    /**
     * Создаем экземпляр общего состояния.
     * @param printers
     * @param decorator
     */
    public LoggerState(Printers printers, LoggerDecorator decorator){
        this.printer = printers;
        this.decorator = decorator;
    }

    /**
     * Метод-заглушка
     * @param stringBuffer
     */
    public void toBuffer(String stringBuffer) throws StateException{
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }

    /**
     * Метод-заглушка
     * @param num
     */
    public void toBuffer(int num) throws StateException{
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }

    /**
     * Метод-заглушка
     * @param msg
     */
    public void toBuffer(char msg){
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }

    /**
     * Метод-заглушка
     * @param msg
     */
    public void toBuffer(boolean msg){
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }

    /**
     * Метод-заглушка
     * @param ref
     */
    public void toBuffer(Object ref){
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }

    /**
     * Метод-заглушка
     */
    public void printBuffer() throws StateException {
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }


}
