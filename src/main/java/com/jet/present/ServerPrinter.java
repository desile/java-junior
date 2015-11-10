package com.jet.present;

import com.acme.exceptions.PrinterException;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Класс для передачи сообщения серверу.
 */
public class ServerPrinter implements Printable {

    private int serverPort;
    private Socket socket;
    private BufferedWriter bw;

    /**
     * Размер буфера в количестве сообщений.
     */
    public static final int SIZE_OF_MSG_BUFFER = 10;
    private int numOfBufferedMsg;//Текущее количество сообщений в буфере.

    /**
     * Открытие потока сообщения с указанным портом.
     * @param port Порт для открытия соединения.
     */
    public ServerPrinter(int port) throws PrinterException {
        numOfBufferedMsg = 0;

        serverPort = port;
        try {
            socket = new Socket("127.0.0.1",serverPort);
            bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),"windows-1251"));
        } catch (IOException e) {
            throw new PrinterException("Problem with opening socket",e);
        }

    }

    /**
     * Передача сообщений серверу с буферизацией.
     * @param msg Сообщение.
     * @throws PrinterException
     */
    @Override
    public void print(String msg) throws PrinterException{
        try{
            bw.write(msg + System.lineSeparator());
            numOfBufferedMsg++;
        }catch (IOException e){
            throw new PrinterException("Can't write to socket",e);
        }
        if(numOfBufferedMsg == SIZE_OF_MSG_BUFFER){
            reset();
        }
    }

    /**
     * Закрытие соединения с сервером.
     * @throws PrinterException
     */
    public void closeConnection() throws PrinterException {
        try {
            bw.close();
        } catch (IOException e) {
            throw new PrinterException("Something goes wrong with closing connection",e);
        }
    }

    /**
     * Очистка буфера.
     * @throws PrinterException
     */
    @Override
    public void reset() throws PrinterException{
        try {
            bw.flush();
        } catch (IOException e) {
            throw new PrinterException("Can't flush stream",e);
        }
        numOfBufferedMsg = 0;
    }
}
