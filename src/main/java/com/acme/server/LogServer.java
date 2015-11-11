package com.acme.server;

import com.acme.exceptions.PrinterException;
import com.jet.present.ConsolePrinter;
import com.jet.present.FilePrinter;
import com.jet.present.Printable;
import com.jet.present.Printers;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.*;

/**
 * Временная реализация сервера логирования.
 */
public class LogServer {

    //TODO: Разгрести исключения

    public static final int MAX_POOL = 5;
    private int serverSocketPort;
    private Printers printers;
    private ServerMessageBuffer smb;
    private ExecutorService pool;
    private final Collection<Socket> clients;

    public LogServer(int portSocket, Printers p){
        serverSocketPort = portSocket;
        printers = p;
        smb = new ServerMessageBuffer();
        pool = Executors.newFixedThreadPool(MAX_POOL);
        clients = new LinkedList<>();
    }

    public void start() throws IOException, PrinterException {
        new Thread(() -> {

            try (ServerSocket ss = new ServerSocket(8999);
            ) {

                while (true) {
                    Socket client = ss.accept();
                    //System.out.println("Connected");
                    synchronized (clients) {
                        clients.add(client);
                    }


                    //pool.submit(() -> {
                    new Thread(() -> {
                        try (
                                BufferedReader fromClientStream = new BufferedReader(new InputStreamReader(client.getInputStream(), "windows-1251"))
                        ) {

                            //  Future<Void> f = pool.submit(() -> {
                            String readLine;

                            /*while ((readLine = fromClientStream.readLine()) != null) {
                                if (smb.add(readLine)) {
                                    smb.flush(printers);
                                }
                                printers.print(readLine);
                            }*/

                            while (true) {
                                if (client.isConnected()) {
                                    if (smb.add(fromClientStream.readLine())) {
                                        smb.flush(printers);
                                    }
                                }
                                smb.flush(printers);
                            }
                            //Если что-то осталось в буфере

                            //
                            // });


                            //f.get();

                        /*} catch (ExecutionException e) {
                            e.getCause();
                        } catch (InterruptedException e) {
                            e.printStackTrace();*/
                        } catch (IOException e) {
                            synchronized (clients) {
                                clients.remove(client);
                            }
                            e.printStackTrace();
                        } catch (PrinterException e) {
                            e.printStackTrace();
                        }
                        //});
                    }).start();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }



    /*public static void main(String[] args) {
        //TEMPORARY! THEN MOVE TO ANOTHER CLASS
        //Printable printer = new ConsolePrinter();//Change to Printers class

        //ServerMessageBuffer smb = new ServerMessageBuffer();
        //ExecutorService pool = Executors.newFixedThreadPool(MAX_POOL);
        //Socket client;

        //File serverlog = new File("serverlog.txt");


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

*/
}