package com.jet.present;

import com.acme.SysoutCaptureAndAssertionAbility;
import com.acme.exceptions.PrinterException;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

import static org.junit.Assert.assertEquals;

public class PrinterTest implements SysoutCaptureAndAssertionAbility{

    Printable printer;

    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
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

}