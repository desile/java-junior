package com.acme.iteration03;

import com.acme.Logger;
import com.acme.SysoutCaptureAndAssertionAbility;
import com.acme.exceptions.LoggerException;
import com.jet.present.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;
import sun.rmi.runtime.Log;

import java.io.IOException;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger;

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


    //TODO: implement logger solution to match specification as tests

    @Test
    public void shouldLogIntegersArray() throws LoggerException {
        //region when
        logger.log(new int[]{-1, 0, 1});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP()
        );
        //endregion
    }

    /*@Test
    public void shouldLogIntegersMatrix() throws IOException {
        //region when
        logger.log(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives matrix: {" + SEP() +
                        "{-1, 0, 1}" + SEP() +
                        "{1, 2, 3}" + SEP() +
                        "{-1, -2, -3}" + SEP() +
                        "}" + SEP()
        );
        //endregion
    }*/
    @Test
    public void shouldLogIntegersMatrix() throws LoggerException {
        //region when
        logger.log(new int[][]{{-1, 0, 1}, {1, 2, 4}, {-1, -2, -3}});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 1" + SEP()
        );
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws LoggerException {
        //region when
        logger.log(new int[][][][] {{{{0}}}});
        //logger.log(new int[][][][] {{{{}}}});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitive: 0" + SEP()
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws LoggerException {
        //region when
        logger.log("str1", "string 2", "str 3", "str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("string: str1" + SEP() + "string: string 2" + SEP() + "string: str 3 (x2)" + SEP());
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws LoggerException {
        //region when
        logger.log(-1, 0, 1, 3);
        //logger.log(new int[][] {{}});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals("primitive: 3" + SEP());
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws LoggerException {
        //region when
        logger.log(1);
        logger.log("str");
        logger.log(Integer.MAX_VALUE);
        logger.log(11);
        logger.close();
        //endregion

        //region then
        assertSysoutContains(1 + "");
        assertSysoutContains("str");
        assertSysoutContains(Integer.MAX_VALUE + "");
        assertSysoutContains(11 + "");
        //endregion
    }

}