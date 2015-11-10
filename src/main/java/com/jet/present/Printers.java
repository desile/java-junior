package com.jet.present;

import com.acme.exceptions.PrinterException;

/**
 * Класс-коллекция принтеров
 */
public class Printers {

    private Printable[] printerCollection;

    /**
     * Инициализация коллекции.
     * @param printers Средства для печати.
     */
    public Printers(Printable... printers){
        this.printerCollection = printers;
    }

    /**
     * Последовательный вызов метода печати у всех принтеров в коллекции.
     * @param msg Сообщение для печати.
     */
    public void print(String msg) throws PrinterException{
        for(Printable p : printerCollection){
            p.print(msg);
        }
    }

    /**
     * Последовательный вызов метода сброса/очистки потока у всех принтеров в коллекции.
     */
    public void reset() throws PrinterException{
        for(Printable p : printerCollection){
            p.reset();
        }
    }

}
