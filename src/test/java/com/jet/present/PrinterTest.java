package com.jet.present;

import com.acme.edu.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

public class PrinterTest implements SysoutCaptureAndAssertionAbility{

    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();

    }

    @Test
    public void printerTest(){
        Printer.print("123");
        Printer.print("lalala");

        assertSysoutEquals("123" + System.lineSeparator() + "lalala" + System.lineSeparator());
    }

}