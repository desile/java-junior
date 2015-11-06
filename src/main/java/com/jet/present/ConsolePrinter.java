package com.jet.present;

/**
 * Класс для вывода в консоль.
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

    /**
     * Переинициализация принтера
     */
    @Override
    public void reset(){
        //пустышка
    }
}
