package com.acme.states;

import com.acme.LoggerDecorator;
import com.jet.present.Printable;

/**
 * Состояние для логирования Integer
 */
public class LoggerSumState extends LoggerState {

    private int buffer;

    private boolean isIntegerOverflow(long number){
        if(number > Integer.MAX_VALUE || number < Integer.MIN_VALUE) {
            return true;
        }
        return false;
    }

    /**
     * Создает экземпляр состояния для входных данных типа Integer
     * @param printer Средство для вывода
     * @param decorator Средство для декорирования вывода
     */
    public LoggerSumState(Printable printer, LoggerDecorator decorator){
        super(printer, decorator);
        buffer = 0;
    }


    /**
     * Добавление в буфер числа, суммируя со значением буфера.
     * Метод может вызвать @see LoggerSumState#printBuffer(), если произошло переполнение буфера.
     * @param msg Число для добавления в буфер.
     */
    @Override
    public void toBuffer(int msg){
        if(isIntegerOverflow((long)msg+buffer)){
            printBuffer();
            buffer = msg;
        }else{
            buffer+=msg;
        }
    }

    /**
     * Печатает буфер состояния и сбрасывает буфер.
     */
    @Override
    public void printBuffer(){
        printer.print(decorator.decorate("INT", ""+buffer));
        buffer = 0;
    }


}
