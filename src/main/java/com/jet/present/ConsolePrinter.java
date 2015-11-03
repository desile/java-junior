package com.jet.present;

/**
 * Created by DeSile on 03.11.2015.
 */
public class ConsolePrinter implements Printable {

    /**
     * Передает в стандартный поток вывода входной строковый аргумент.
     * @param msg Данные для вывода
     */
    public void print(String msg){
        System.out.println(msg);
    }

    /**
     * Передает в стандартный поток вывода входной строковый аргумент.
     * @param msg Данные для вывода
     * @param newLine Если true - то каретка после вывода аргумента будет переведена на новую строку
     */
    public void print(String msg, boolean newLine){
        if(newLine) {
            print(msg);
        }
        else {
            System.out.print(msg);
        }
    }

}
