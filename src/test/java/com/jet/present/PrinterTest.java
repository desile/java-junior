package com.jet.present;

import com.acme.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

public class PrinterTest implements SysoutCaptureAndAssertionAbility{

    Printable printer;

    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
        printer = new ConsolePrinter();
    }

    @Test
    public void consolePrinterTest(){
        printer.print("123");
        printer.print("Immolate improved!");

        assertSysoutEquals("123" + System.lineSeparator() + "Immolate improved!" + System.lineSeparator());
    }

    @Test(expected = NullPointerException.class)
    public void shouldPrinterThrowExceptionWhenGettingNullString(){
        String nullString = null;
        printer.print(nullString);
    }

}