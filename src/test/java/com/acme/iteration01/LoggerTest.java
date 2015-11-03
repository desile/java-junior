package com.acme.iteration01;

import com.acme.Logger;
import com.acme.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    //private static final String ☺ = System.lineSeparator();

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
    }
    //endregion

    @Test
    public void shouldLogInteger() throws IOException {
        //region when
        Logger.log(1);
        Logger.log(0);
        Logger.log(-1);
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 0" + SEP());
        //endregion
    }

    @Test
    public void shouldLogByte() throws IOException {
        //region when
        Logger.log((byte) 1);
        Logger.log((byte) 0);
        Logger.log((byte) -1);
        Logger.close();
        //endregion


        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("0");
        //endregion
    }


    //TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogChar() throws IOException {
        //region when
        Logger.log('a');
        Logger.log('b');
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws IOException {
        //region when
        Logger.log("test string 1");
        Logger.log("other str");
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("string: ");
        assertSysoutContains("test string 1");
        assertSysoutContains("other str");
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws IOException {
        //region when
        Logger.log(true);
        Logger.log(false);
        Logger.log(true);
        Logger.close();
        //endregion

        //region then
        //assertSysoutContains("primitive: ");
        //assertSysoutContains("true");
        //assertSysoutContains("false");
        assertSysoutEquals("primitive: true" + SEP() + "primitive: false" + SEP() + "primitive: true" + SEP());
        //endregion
    }

    @Test
    public void shouldLogReference() throws IOException {
        //region when
        Logger.log(new Object());
        Logger.log(new Object());
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("reference: ");
        assertSysoutContains("@");
        //endregion
    }
}