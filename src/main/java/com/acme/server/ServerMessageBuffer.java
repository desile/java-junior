package com.acme.server;

import com.acme.exceptions.PrinterException;
import com.jet.present.Printable;

import java.util.*;

/**
 * Буфер сообщений на стороне сервера
 */
public class ServerMessageBuffer {

    /**
     * Размер буфера.
     */
    public static final int MAX_SIZE_OF_MSG_BUFFER = 50;
    private List<String> buffer;

    /**
     * Инциализация буфера.
     */
    public ServerMessageBuffer(){
        buffer = new ArrayList<>(MAX_SIZE_OF_MSG_BUFFER);
    }

    /**
     * Добавить сообщение в буфер.
     * @param msg Сообщение.
     * @return вернет false - если буфер заполнен и требует очистки, true - иначе.
     */
    public boolean add(String msg){
        buffer.add(msg);
        return buffer.size() == MAX_SIZE_OF_MSG_BUFFER;
    }

    /**
     * Очистка и печать буфера.
     * @param printer Средство для печати.
     */
    public void flush(Printable printer) throws PrinterException{
        sortErrors();
        for(String s : buffer){
            printer.print(s);
        }
    }

    //Сортировка буфера (помещаем ерроры на верх буфера)
    private void sortErrors(){
        Collections.sort(buffer, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if("[ERROR]".equals(o1.substring(0,7)) && !"[ERROR]".equals(o2.substring(0,7))){
                    return -1;
                }
                else return 1;
            }
        });
    }

}
