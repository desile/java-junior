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

    @Before
    public void initFactory(){
        factory = mock(LoggerStateFactory.class);

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
