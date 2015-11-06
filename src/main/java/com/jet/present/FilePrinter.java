package com.jet.present;

import java.io.*;
import java.util.Date;

/**
 * Класс для вывода сообщения в лог-файл c генерируемым именем, зависящим от текущих даты и времени.
 */
public class FilePrinter implements Printable {

    private File outputFile;
    private static long fileId = 2;

    private String filePrefix;

    private String makeFileName(){
        return "logs" + File.separator + filePrefix + (new Date(System.currentTimeMillis()).toString()).replace(" ","_").replace(":","-") + "_log";
    }

    private void makeNewFile(){
        outputFile = new File(makeFileName()+".txt");
        if(outputFile.exists()){
            outputFile = new File(makeFileName() + "(" + fileId++ + ").txt");
        }
        else{
            fileId = 2;
        }
    }

    /**
     * Конструктор, добавляющий к лог-файлу префикс.
     * @param prefix Префикс к имени файла.
     */
    public FilePrinter(String prefix){
        filePrefix = prefix;
        makeNewFile();
    }

    /**
     * Конструктор, создающий лог файл без добавления префикса.
     */
    public FilePrinter(){
        new FilePrinter("");
    }


    /**
     * Записывает сообщение в файл.
     * @param msg Сообщение.
     */
    @Override
    public void print(String msg) {
        try (FileWriter fw = new FileWriter(outputFile, true)) {
            fw.write(msg + System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Возвращает текущий логируемый файл.
     * @return
     */
    public File getCurrentFile(){
        return outputFile;
    }

    /**
     * Создание нового файла для записи логов.
     */
    public void reset(){
        makeNewFile();
    }

}
