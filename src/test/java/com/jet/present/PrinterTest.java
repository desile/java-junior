package com.jet.present;

import com.acme.Logger;
import com.acme.SysoutCaptureAndAssertionAbility;
import com.acme.exceptions.LoggerException;
import com.acme.exceptions.PrinterException;
import com.acme.server.LogServer;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class PrinterTest implements SysoutCaptureAndAssertionAbility{

    Printable printer;

    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
    }

    @Test
    public void printerCollectionTest() throws PrinterException {
        String testMsg = "Test message";
        ConsolePrinter cp1 = mock(ConsolePrinter.class);
        ConsolePrinter cp2 = mock(ConsolePrinter.class);
        ConsolePrinter cp3 = mock(ConsolePrinter.class);
        Printers ps = new Printers(cp1,cp2,cp3);
        ps.print(testMsg);
        verify(cp1).print(testMsg);
        verify(cp2).print(testMsg);
        verify(cp3).print(testMsg);
    }

    @Test
    public void consolePrinterTest() throws PrinterException{
        printer = new ConsolePrinter();
        printer.print("123");
        printer.print("Immolate improved!");

        assertSysoutEquals("123" + SEP() + "Immolate improved!" + SEP());
    }

    @Test(expected = PrinterException.class)
    public void shouldPrinterThrowExceptionWhenGettingNullString() throws PrinterException{
        printer = new ConsolePrinter();
        String nullString = null;
        printer.print(nullString);
    }

    @Test
    public void filePrinterTest() throws PrinterException, IOException {
        String fileName = "junit_";
        printer = new FilePrinter(fileName);
        String str1 = "test string 1";
        String str2 = "test string 2";
        printer.print(str1);
        printer.print(str2);
        printer.reset();
        String fileStr = FileUtils.readFileToString(((FilePrinter)printer).getCurrentFile());
        assertEquals(str1 + SEP() + str2 + SEP(), fileStr);
    }

    @Test(expected = PrinterException.class)
    public void shouldServerPrinterThrowExceptionWhenTryingToConnectToIncorrectPort() throws PrinterException{
        int numberPortThatShouldBeFreeOrEmpty = 22222;
        ServerPrinter sp = new ServerPrinter(numberPortThatShouldBeFreeOrEmpty);
    }

    @Test
    public void shouldServerWork() throws IOException, PrinterException, LoggerException, InterruptedException {
        Printers p = mock(Printers.class);
        LogServer serv = new LogServer(50210,p);
        serv.start();
        Thread.sleep(2000);
        Logger l = new Logger(new ServerPrinter(50210));
        l.log(true);
        l.close();
    }


}