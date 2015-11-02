package com.acme.iteration03;

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


    //TODO: implement Logger solution to match specification as tests

    @Test
    public void shouldLogIntegersArray() throws IOException {
        //region when
        Logger.log(new int[]{-1, 0, 1});
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
        Logger.log(new int[][] {{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        Logger.close();
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
        Logger.log(new int[][][][] {{{{0}}}});
        Logger.close();
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
        Logger.log("str1", "string 2", "str 3", "str 3");
        Logger.close();
        //endregion

        //region then
        assertSysoutContains("string: str1" + SEP() + "string: string 2" + SEP() + "string: str 3 (x2)" + SEP());
        //endregion
    }

    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException {
        //region when
        Logger.log(-1, 0, 1, 3);
        Logger.close();
        //endregion

        //region then
        assertSysoutEquals("primitives: 3" + SEP());
        //endregion
    }

    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException {
        //region when
        Logger.log(1);
        Logger.log("str");
        Logger.log(Integer.MAX_VALUE);
        Logger.log(11);
        Logger.close();
        //endregion

        //region then
        assertSysoutContains(1 + "");
        assertSysoutContains("str");
        assertSysoutContains(Integer.MAX_VALUE + "");
        assertSysoutContains(11 + "");
        //endregion
    }

}