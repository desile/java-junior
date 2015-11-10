package com.acme.states;

import com.acme.Logger;
import com.acme.decorator.LoggerDecorator;
import com.acme.exceptions.PrinterException;
import com.acme.exceptions.StateException;
import com.jet.present.Printers;

/**
 * Состояние для логирования Integer
 */
public class LoggerSumState extends LoggerState {

    private int buffer;

    /**
     * Создает экземпляр состояния для входных данных типа Integer
     * @param printer Средство для вывода
     * @param decorator Средство для декорирования вывода
     */
    public LoggerSumState(Printers printer, LoggerDecorator decorator){
        super(printer, decorator);
        buffer = 0;
    }

    private boolean isIntegerOverflow(long number){
        return number > Integer.MAX_VALUE || number < Integer.MIN_VALUE;
    }




    /**
     * Добавление в буфер числа, суммируя со значением буфера.
     * Метод может вызвать @see LoggerSumState#printBuffer(), если произошло переполнение буфера.
     * @param msg Число для добавления в буфер.
     */
    @Override
    public void toBuffer(int msg) throws StateException{
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
    public void printBuffer() throws StateException{
        try {
            printer.print(Logger.MSG + decorator.decorate("INT", ""+buffer));
        } catch (PrinterException e) {
            throw new StateException("Cant print sum buffer",e);
        }
        buffer = 0;
    }


}
