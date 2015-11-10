package com.jet.present;

import com.acme.exceptions.PrinterException;

import java.io.*;

/**
 * Класс для вывода сообщения в лог-файл c генерируемым именем, зависящим от текущих даты и времени.
 */
public class FilePrinter implements Printable {

    private File outputFile;

    private String filePrefix;
    private BufferedWriter bw;

    private String makeFileName(){
        return "log";
    }

    private void makeNewFile(){
        outputFile = new File(filePrefix + makeFileName()+".txt");
    }

    /**
     * Конструктор, добавляющий к лог-файлу префикс.
     * @param prefix Префикс к имени файла.
     */
    public FilePrinter(String prefix) throws PrinterException{
        filePrefix = prefix;
        makeNewFile();
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePrefix + makeFileName()+".txt"), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new PrinterException("Unknown encoding",e);
        } catch (FileNotFoundException e) {
            throw new PrinterException("File is not found",e);
        }
    }


    /**
     * Записывает сообщение в файл.
     * @param msg Сообщение.
     */
    @Override
    public void print(String msg) throws PrinterException {
        try {
            bw.write(msg + System.lineSeparator());
        } catch (IOException e) {
            throw new PrinterException("Problem with writing to file",e);
        }
    }

    /**
     * Возвращает текущий логируемый файл.
     * @return текущий логируемый файл.
     */
    public File getCurrentFile(){
        return outputFile;
    }

    /**
     * Сброс буфера
     */
    @Override
    public void reset() throws PrinterException {
        try {
            bw.flush();
        } catch (IOException e) {
            throw new PrinterException("Cant flush stream",e);
        }
    }

}
