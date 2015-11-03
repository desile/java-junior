package com.acme.iteration03;

import com.acme.Logger;
import com.acme.SysoutCaptureAndAssertionAbility;
import org.junit.Before;
import org.junit.Test;
import org.junit.Ignore;

import java.io.IOException;

@Ignore
public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    private Logger logger = new Logger();

    //region given
    @Before
    public void setUpSystemOut() throws IOException {
        flushSysout();
        captureSysout();
    }
    //endregion


    //TODO: implement logger solution to match specification as tests

    @Test
    public void shouldLogIntegersArray() throws IOException {
        //region when
        logger.log(new int[]{-1, 0, 1});
        //endregion

        //region then
        assertSysoutEquals(
            "primitives: 0" + SEP()
        );
        //endregion
    }

    @Test
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
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException {
        //region when
        logger.log(new int[][][][] {{{{0}}}});
        logger.close();
        //endregion

        //region then
        assertSysoutEquals(
                "primitives multimatrix: {" + SEP() +
                        "{" + SEP() + "{" + SEP() + "{" + SEP() +
                        "0" + SEP() +
                        "}" + SEP() + "}" + SEP() + "}" + SEP() +
                        "}" + SEP()
        );
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException {
        //region when
        logger.log("str1", "string 2", "str 3", "str 3");
        logger.close();
        //endregion

        //region then
        assertSysoutContains("string: str1" + SEP() + "string: string 2" + SEP() + "string: str 3 (x2)" + SEP());
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        logger.log(-1, 0, 1, 3);
        logger.close();
        //endregion

        //region then
        assertSysoutEquals("primitives: 3" + SEP());
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
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