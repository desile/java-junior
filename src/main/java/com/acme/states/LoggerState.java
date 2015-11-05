package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Created by DeSile on 02.11.2015.
 */

/**
 * Базовый класс состояния, который ирает роль как родителя всех специфических состояний, так и заглушки для ситуаций,<br>
 * когда состояние нам требуется, но мы не ожидаем от него никакого поведения.
 */
public class LoggerState {

    protected Printable printer;
    protected LoggerDecorator decorator;

    /**
     * Создаем экземпляр общего состояния.
     * @param printer
     * @param decorator
     */
    public LoggerState(Printable printer, LoggerDecorator decorator){
        this.printer = printer;
        this.decorator = decorator;
    }

    /**
     * Метод-заглушка
     * @param stringBuffer
     */
    public void toBuffer(String stringBuffer) {
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }

    /**
     * Метод-заглушка
     * @param num
     */
    public void toBuffer(int num) {
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
    public void printBuffer(){
        //Метод заглушка, переопределяетя под специфические задачи в дочерних классах.
    }


}
