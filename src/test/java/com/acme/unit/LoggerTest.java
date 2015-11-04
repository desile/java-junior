package com.acme.unit;

import com.acme.Logger;
import com.acme.states.*;
import org.junit.Before;
import org.junit.Test;

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


}
