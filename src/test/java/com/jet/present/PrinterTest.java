package com.jet.present;

import com.acme.SysoutCaptureAndAssertionAbility;
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
        Printer.print("Immolate improved!");
        Printer.print("sup",false);

        assertSysoutEquals("123" + System.lineSeparator() + "Immolate improved!" + System.lineSeparator() + "sup");
    }

}