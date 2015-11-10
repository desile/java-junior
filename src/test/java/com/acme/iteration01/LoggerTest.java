package com.acme.iteration01;

import com.acme.Logger;
import com.acme.SysoutCaptureAndAssertionAbility;
import com.acme.exceptions.LoggerException;
import com.jet.present.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.io.*;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    //private static final String ☺ = System.lineSeparator();
    private Logger logger;

    public LoggerTest() throws  LoggerException{
        logger = new Logger(new ConsolePrinter());
    }

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
    }
    //endregion
    @Test
    public void shouldLogInteger() throws LoggerException {
        //region when
        logger.log(1);
        logger.log(0);
        logger.log(-1);
        logger.close();
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutContains("primitive: ");
        assertSysoutEquals("primitive: 0" + SEP() + "primitive: 0" + SEP());
        //endregion
    }

    @Test
    public void shouldLogByte() throws LoggerException {
        //region when
        logger.log((byte) 1);
        logger.log((byte) 0);
        logger.log((byte) -1);
        logger.close();
        //endregion


        //region then
        assertSysoutContains("primitive: ");
        assertSysoutContains("0");
        //endregion
    }


    //TODO: implement logger solution to match specification as tests

    @Test
    public void shouldLogChar() throws LoggerException {
        //region when
        logger.log('a');
        logger.log('b');
        logger.close();
        //endregion

        //region then
        assertSysoutContains("char: ");
        assertSysoutContains("a");
        assertSysoutContains("b");
        //endregion
    }

    @Test
    public void shouldLogString() throws LoggerException {
        //region when
        logger.log("test string 1");
        logger.log("test string 2");
        logger.log("other str");
        logger.close();
        //endregion

        //region then
        assertSysoutEquals("string: test string 1" + SEP() + "string: test string 2" + SEP() + "string: other str" + SEP());
        //endregion
    }

    @Test
    public void shouldLogBoolean() throws LoggerException {
        //region when
        logger.log(true);
        logger.log(false);
        logger.log(true);
        logger.close();
        //endregion

        //region then
        //assertSysoutContains("primitive: ");
        //assertSysoutContains("true");
        //assertSysoutContains("false");
        assertSysoutEquals("primitive: true" + SEP() + "primitive: false" + SEP() + "primitive: true" + SEP());
        //endregion
    }

    @Test
    public void shouldLogReference() throws LoggerException {
        //region when
        Object objnull = null;
        Object obj = new Object();
        logger.log(objnull);
        logger.log(obj);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals("reference: " + objnull + SEP() + "reference: " + obj + SEP());
        //endregion
    }
}