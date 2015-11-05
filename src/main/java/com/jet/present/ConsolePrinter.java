package com.jet.present;

/**
 * Created by DeSile on 03.11.2015.
 */
public class ConsolePrinter implements Printable {

    /**
     * Передает в стандартный поток вывода входной строковый аргумент.
     * @param msg Данные для вывода
     */
    @Override
    public void print(String msg){
        if(msg == null) throw new NullPointerException("String is null");
        System.out.println(msg);
    }

}
