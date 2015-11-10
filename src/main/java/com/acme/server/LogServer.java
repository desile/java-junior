package com.acme.server;

import com.acme.exceptions.PrinterException;
import com.jet.present.ConsolePrinter;
import com.jet.present.FilePrinter;
import com.jet.present.Printable;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Временная реализация сервера логирования.
 */
public class LogServer {

    //TODO: Разгрести исключения

    public static final int MAX_POOL = 5;



    public static void main(String[] args) {

        //TEMPORARY! THEN MOVE TO ANOTHER CLASS
        Printable printer = new ConsolePrinter();//Change to Printers class

        ServerMessageBuffer smb = new ServerMessageBuffer();
        ExecutorService pool = Executors.newFixedThreadPool(MAX_POOL);
        Socket client;
        File serverlog = new File("serverlog.txt");


        try( ServerSocket logserv = new ServerSocket(8999)) {

            client = logserv.accept();
            BufferedReader fromClientStream = new BufferedReader(new InputStreamReader(client.getInputStream(),"windows-1251"));
            Printable filePrinter = new FilePrinter("server");

            Thread t = new Thread(()->{



                Future<Void> f = pool.submit(() -> {
                        String readLine;
                        while ((readLine = fromClientStream.readLine()) != null) {
                            if (smb.add(readLine)) {
                                smb.flush(printer);
                            }
                            printer.print(readLine);
                        }
                        //Если что-то осталось в буфере
                        smb.flush(printer);
                        return null;
                    });



                try{
                    f.get();
                }catch (ExecutionException e){
                    e.getCause();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });


        t.run();


            //????
        }catch(IOException e){
            e.printStackTrace();
        } catch (PrinterException e) {
            e.printStackTrace();
        }

    }

}
