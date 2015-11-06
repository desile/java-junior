package com.jet.present;

import com.acme.SysoutCaptureAndAssertionAbility;
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
    public void consolePrinterTest(){
        printer = new ConsolePrinter();
        printer.print("123");
        printer.print("Immolate improved!");

        assertSysoutEquals("123" + SEP() + "Immolate improved!" + SEP());
    }

    @Test(expected = NullPointerException.class)
    public void shouldPrinterThrowExceptionWhenGettingNullString(){
        String nullString = null;
        printer.print(nullString);
    }

    @Test
    public void filePrinterTest() throws IOException {
        String fileName = "junit_";
        printer = new FilePrinter(fileName);
        String str1 = "test string 1";
        String str2 = "test string 2";
        printer.print(str1);
        printer.print(str2);
        String fileStr = FileUtils.readFileToString(((FilePrinter)printer).getCurrentFile());
        assertEquals(str1 + SEP() + str2 + SEP(), fileStr);
    }

}