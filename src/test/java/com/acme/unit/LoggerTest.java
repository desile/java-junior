package com.acme.unit;

import com.acme.Logger;
import com.acme.states.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.Mockito.*;

/**
 * Created by DeSile on 05.11.2015.
 */
public class LoggerTest {

    private Logger logger = new Logger();
    private LoggerStateFactory factory;
    private LoggerBoolState boolState;
    private LoggerStringState stringState;
    private LoggerObjState objState;
    private LoggerSumState sumState;
    private LoggerCharState charState;
    private LoggerState comState;

    @Before
    public void initFactory(){
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
    public void shouldStatesChangedCorrectly(){
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
    public void shouldElementsInArraySetToSumState() {
        logger.log(1, 2, 3, 4);
        verify(factory, times(4)).setToSumState(anyObject()); //summing for times for array of 4 elements
    }

    @Test
    public void shouldStringsInArraySetToStringState(){
        logger.log("test1","test1","test2");
        verify(factory, times(3)).setToStringState(anyObject());
    }

    @Test
    public void shouldElementsInTwoDimensionalArraySetToSumState(){
        logger.log(new int[][]{{1,2,3},{2,3,4},{0,1,0}});
        verify(factory, times(9)).setToSumState(anyObject());
    }

    @Test
    public void shouldElementsInFourDimensionalArraySetToSumState(){
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
    public void shouldEmptyArrayThrowsException(){
        int[] nullArr = null;
        logger.log(nullArr);
    }

    @Test(expected = NullPointerException.class)
    public void shouldEmptyMultiArrayWithNullArraysThrowsException(){
        int[][] nullArr = new int[2][];
        nullArr[0] = null;
        nullArr[1] = null;
        logger.log(nullArr);
    }


}
