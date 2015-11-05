package com.acme.decorator;

import java.util.Map;

/**
 * Интерфейс декорирования.
 */
public interface LoggerDecorator {

    /**
     * Декорирование сообщения.
     * @param src Ключ для словаря декораций.
     * @param msg Сообщения для декорирования.
     * @return Задекорированное сообщение
     */
    String decorate(String src, String msg);

    /**
     * Установить декоратору новый словарь декораций.
     * @param decor Словарь декораций.
     */
    void setDecor(Map<String,String> decor);

}
