package com.acme.states;

import com.acme.decorator.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Состояние для логирования String
 */
public class LoggerStringState extends LoggerState {

    private String buffer;
    private int numOfBuffer;

    /**
     * Создает экземпляр состояния для входных данных типа String
     * @param printer Средство для вывода
     * @param decorator Средство для декорирования вывода
     */
    public LoggerStringState(Printable printer, LoggerDecorator decorator){
        super(printer,decorator);
        buffer = "";
        numOfBuffer = 0;
    }


    /**
     * Добавление в буфер строки, со сравнением с текущем значениям буфера.
     * Если в буфере лежит такая же строка, то строки сливаются.
     * Если в буфере другая строка, то буфер печатается и перезаписывается новой строкой.
     * @param msg Строка для добавления в буфер.
     */
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

    /**
     * Печатает буфер состояния и сбрасывает буфер.
     */
    @Override
    public void printBuffer(){
        printer.print(decorator.decorate("STRING",(numOfBuffer < 2) ? buffer : buffer + " (x" + numOfBuffer + ")"));
            buffer = "";
            numOfBuffer = 0;

    }

}
