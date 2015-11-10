package com.jet.present;

import com.acme.exceptions.PrinterException;


/**
 * Интерфейс вывода сообщений.
 */
public interface Printable {

	/**
	 * Печать сообщения.
	 * @param msg Сообщение.
	 */
	void print(String msg) throws PrinterException;

	/**
	 * Очистка потока вывода, если он используется.
	 */
	void reset() throws PrinterException;
}