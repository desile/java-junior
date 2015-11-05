package com.acme.decorator;

import com.acme.decorator.LoggerDecorator;

import java.util.Map;

/**
 * Created by DeSile on 03.11.2015.
 */

/**
 * Префиксный декоратор.
 */
public class LoggerPrefixDecorator implements LoggerDecorator {

    private Map<String,String> decor;

    /**
     * Конструктор, устанавливающий словарь декорирования.
     * @param decor Словарь декорирования.
     */
    public LoggerPrefixDecorator(Map<String,String> decor){
        this.decor = decor;
    }

    /**
     * Помещает декор перед сообщением, создавая строку вида "<декор>: <сообщение>"
     * @param src Ключ для словаря декораций.
     * @param msg Сообщения для декорирования.
     * @return Задекорированное сообщение.
     */
    @Override
    public String decorate(String src, String msg) {
        return decor.get(src) + ": " + msg;
    }

    /**
     * Установка нового словаря декораций.
     * @param decor Словарь декораций.
     */
    @Override
    public void setDecor(Map<String, String> decor) {
        this.decor = decor;
    }


}
