package com.jet.present;

public class Printer{

	private Printer(){

	}

	/**
	 * Передает в стандартный поток вывода входной строковый аргумент.
	 * @param msg Данные для вывода
	 */
	public static void print(String msg){
		System.out.println(msg);
	}
}