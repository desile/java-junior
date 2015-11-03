package com.acme.unit;

import com.acme.Logger;
import com.acme.states.LoggerState;
import com.acme.states.LoggerStateFactory;
import com.acme.states.LoggerStringState;
import com.jet.present.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerTest {

    private LoggerStateFactory logger;
    private ConsolePrinter printer;


    @Before
    public void initLogger(){
        printer = mock(ConsolePrinter.class);
        logger = new LoggerStateFactory(printer);
    }

    @Test
    public void shouldStringBufferPrintedInsideStringState(){
        LoggerState state = logger.setToStringState(new LoggerState(printer));
        logger.setToStringState(state);
        logger.setToStringState(state);
        logger.setToStringState(state);
        verify(printer,times(0)).print(anyString());
    }


}
