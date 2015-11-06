package com.jet.present;

/**
 * Интерфейс вывода сообщений.
 */
public interface Printable {

	/**
	 * Печать сообщения.
	 * @param msg Сообщение.
	 */
	void print(String msg);

	/**
	 * ...
	 */
	void reset();
}