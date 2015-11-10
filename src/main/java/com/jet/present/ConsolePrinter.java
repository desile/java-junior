package com.jet.present;

import com.acme.exceptions.PrinterException;

/**
 * Класс для вывода в консоль.
 */
public class ConsolePrinter implements Printable {

    /**
     * Передает в стандартный поток вывода входной строковый аргумент.
     * @param msg Данные для вывода
     */
    @Override
    public void print(String msg) throws PrinterException{
        if(msg == null) throw new PrinterException("String is null");
        System.out.println(msg);
    }

    /**
     * Переинициализация принтера
     */
    @Override
    public void reset(){
        //пустышка
    }
}
