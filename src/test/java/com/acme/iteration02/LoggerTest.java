package com.acme.iteration02;

import com.acme.Logger;
import com.acme.SysoutCaptureAndAssertionAbility;
import com.acme.exceptions.LoggerException;
import com.jet.present.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.io.IOException;


@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    public LoggerTest() throws LoggerException {
        logger = new Logger(new ConsolePrinter());
    }

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
    }
    //endregion

    private Logger logger;


    //TODO: implement logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws LoggerException{
        //region when
        logger.log("str 1");
        logger.log(1);
        logger.log(2);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + SEP() +
            "primitive: 3" + SEP() +
            "string: str 2" + SEP() +
            "primitive: 0" + SEP()
        );
        //endregion
    }

    @Test
    public void shouldlogWithoutInfoCorrectlyIntegerOverflowWhenSequentIntegers() throws LoggerException{
        //region when
        logger.log("str 1");
        logger.log(10);
        logger.log(Integer.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + SEP() +
            "primitive: 10" + SEP() +
            "primitive: " + Integer.MAX_VALUE + SEP() +
            "string: str 2" + SEP() +
            "primitive: 0" + SEP()
        );
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() throws LoggerException{
        //region when
        logger.log("str 1");
        logger.log((byte) 10);
        logger.log((byte) Byte.MAX_VALUE);
        logger.log("str 2");
        logger.log(0);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + SEP() +
            "primitive: 137" + SEP() +
            "string: str 2" + SEP() +
            "primitive: 0" + SEP()
        );
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws LoggerException {
        //region when
        logger.log("str 1");
        logger.log("str 2");
        logger.log("str 2");
        logger.log(0);
        logger.log("str 2");
        logger.log("str 3");
        logger.log("str 3");
        logger.log("str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
            "string: str 1" + SEP() +
            "string: str 2 (x2)" + SEP() +
            "primitive: 0" + SEP() +
            "string: str 2" + SEP() +
            "string: str 3 (x3)" + SEP()
        );
        //endregion
    }


}