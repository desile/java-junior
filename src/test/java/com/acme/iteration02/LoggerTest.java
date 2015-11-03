package com.acme.iteration02;

import com.acme.Logger;
import com.acme.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
    }
    //endregion

    private Logger logger = new Logger();


    //TODO: implement logger solution to match specification as tests

    @Test
    public void shouldLogSequentIntegersAsSum() throws IOException {
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
    public void shouldlogWithoutInfoCorrectlyIntegerOverflowWhenSequentIntegers() {
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
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() {
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
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException {
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