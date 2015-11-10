package com.acme.unit;

import com.acme.Logger;
import com.acme.LoggerStateFactory;
import com.acme.exceptions.LoggerException;
import com.acme.exceptions.StateException;
import com.acme.states.*;
import com.jet.present.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by DeSile on 05.11.2015.
 */
public class LoggerTest {

    private Logger logger;
    private LoggerStateFactory factory;
    private LoggerBoolState boolState;
    private LoggerStringState stringState;
    private LoggerObjState objState;
    private LoggerSumState sumState;
    private LoggerCharState charState;
    private LoggerState comState;

    public LoggerTest() throws LoggerException{
        logger = new Logger(new ConsolePrinter());
    }

    @Before
    public void initFactory() throws StateException{
        factory = mock(LoggerStateFactory.class);
        boolState = mock(LoggerBoolState.class);
        stringState = mock(LoggerStringState.class);
        objState = mock(LoggerObjState.class);
        sumState = mock(LoggerSumState.class);
        charState = mock(LoggerCharState.class);
        comState = mock(LoggerState.class);

        stub(factory.setToBoolState(anyObject())).toReturn(boolState);
        stub(factory.setToCharState(anyObject())).toReturn(charState);
        stub(factory.setToComState(anyObject())).toReturn(comState);
        stub(factory.setToObjState(anyObject())).toReturn(objState);

        stub(factory.setToSumState(anyObject())).toReturn(sumState);

        stub(factory.setToStringState(anyObject())).toReturn(stringState);

        logger.setStateFactory(factory);
    }

    @Test
    public void shouldStatesChangedCorrectly() throws StateException,LoggerException {
        logger.log(10);
        logger.log(20);
        logger.log(true);
        logger.log(false);
        logger.close();
        logger.log(new Object());
        logger.log('a');
        logger.log("test");
        logger.close();

        verify(factory, times(2)).setToSumState(anyObject());
        verify(factory, times(2)).setToBoolState(anyObject());
        verify(factory, times(1)).setToObjState(anyObject());
        verify(factory, times(1)).setToCharState(anyObject());
        verify(factory, times(1)).setToStringState(anyObject());
        verify(factory, times(2)).setToComState(anyObject());

    }

    @Test
    public void shouldElementsInArraySetToSumState() throws StateException,LoggerException{
        logger.log(1, 2, 3, 4);
        verify(factory, times(4)).setToSumState(anyObject()); //summing for times for array of 4 elements
    }

    @Test
    public void shouldStringsInArraySetToStringState() throws StateException,LoggerException{
        logger.log("test1","test1","test2");
        verify(factory, times(3)).setToStringState(anyObject());
    }

    @Test
    public void shouldElementsInTwoDimensionalArraySetToSumState() throws StateException,LoggerException{
        logger.log(new int[][]{{1,2,3},{2,3,4},{0,1,0}});
        verify(factory, times(9)).setToSumState(anyObject());
    }

    @Test
    public void shouldElementsInFourDimensionalArraySetToSumState()throws StateException,LoggerException{
        int[][][][] multiArr = new int[3][3][3][3];
        for(int[][][] i3 : multiArr){
            for(int[][] i2: i3){
                for(int[] i1: i2){
                    Arrays.fill(i1,1);
                }
            }
        }
        logger.log(multiArr);
        verify(factory,times(3*3*3*3)).setToSumState(anyObject());
    }

    @Test(expected = NullPointerException.class)
    public void shouldLoggedNullArrayThrowsException()throws StateException,LoggerException{
        int[] nullArr = null;
        logger.log(nullArr);
        logger.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldLoggedEmptyArrayThrowsException()throws StateException,LoggerException{
        int[] emptyArr = {};
        logger.log(emptyArr);
        logger.close();
    }

    @Test(expected = NullPointerException.class)
    public void shouldLoggedEmptyMultiArrayWithNullArraysThrowsException()throws StateException,LoggerException{
        int[][] nullArr = new int[2][];
        nullArr[0] = null;
        nullArr[1] = null;
        logger.log(nullArr);
        logger.close();
    }

    @Test(expected = LoggerException.class)
    public void shouldLoggedNullStringThrowsException()throws StateException,LoggerException{
        String nullString = null;
        logger.log(nullString);
        logger.close();
    }

    @Test(expected = LoggerException.class)
    public void shouldLoggedEmptyStringThrowsException()throws StateException,LoggerException{
        String emptyString = "";
        logger.log(emptyString);
        logger.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldLoggedEmptyTwoDimensionalArraysThrowsException()throws StateException,LoggerException{
        int[][] emptyArr = new int[0][0];
        logger.log(emptyArr);
        logger.close();
    }

    @Test(expected = NullPointerException.class)
    public void shouldLoggedNullTwoDimensionalArraysThrowsException()throws StateException,LoggerException{
        int[][] emptyArr = null;
        logger.log(emptyArr);
        logger.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldLoggedEmptyStringArrayThrowsException()throws StateException,LoggerException{
        logger.log(new String[]{});
        logger.close();
    }

    @Test(expected = NullPointerException.class)
    public void shouldLoggedNullStringArrayThrowsException()throws StateException,LoggerException{
        String[] nullArr = null;
        logger.log(nullArr);
        logger.close();
    }

    @Test(expected = NullPointerException.class)
    public void shouldLoggedNullMultiArrayThrowsException()throws StateException,LoggerException{
        int[][][][] nullArr = null;
        logger.log(nullArr);
        logger.close();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldLoggedEmptyMultiArrayThrowsException()throws StateException,LoggerException{
        int[][][][] emptyArr = new int[0][0][0][0];
        logger.log(emptyArr);
        logger.close();
    }



}
