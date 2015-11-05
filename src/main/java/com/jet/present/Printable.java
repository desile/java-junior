package com.jet.present;

/**
 * Интерфейс вывода сообщений.
 */
public interface Printable {

	/**
	 * Печать сообщения.
	 * @param msg Сообщение.
	 * @throws NullPointerException
	 */
	void print(String msg) throws NullPointerException;

}