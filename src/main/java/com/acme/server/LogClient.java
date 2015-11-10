package com.acme.server;

import com.acme.Logger;
import com.acme.exceptions.LoggerException;
import com.acme.exceptions.PrinterException;
import com.jet.present.ConsolePrinter;
import com.jet.present.Printable;
import com.jet.present.ServerPrinter;


/**
 * Created by DeSile on 06.11.2015.
 */
public class LogClient {

    public static void main(String[] args){
        Printable printer = null;
        try {
            printer = new ServerPrinter(8999);
        } catch (PrinterException e) {
            e.printStackTrace();
        }
        Logger logger;
        try {
            logger = new Logger(printer,new ConsolePrinter());


            logger.log(123);
            logger.log(5);
            logger.log("Axaxa");
            logger.log("ZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
            logger.close();
            logger.log(true);
            logger.log("русский текст");
            logger.log(false);
            logger.close();


            if(printer instanceof ServerPrinter){
                ((ServerPrinter) printer).closeConnection();
            }

        } catch (PrinterException e) {
            //TEMPORARY EMPTINESS
        } catch (LoggerException e){
            try {
                printer.print("[ERROR]: " + e);
                for(Throwable supressed : e.getSuppressed()){
                    printer.print(Logger.ERR_MSG + supressed);
                }
            } catch (PrinterException e1) {
                //TEMPORARY EMPTINESS
            }
        }finally {
            try {
                ((ServerPrinter)printer).closeConnection();
            } catch (PrinterException e) {
                e.printStackTrace();
                //TEMPORARY EMPTINESS
            }
        }


    }

}
