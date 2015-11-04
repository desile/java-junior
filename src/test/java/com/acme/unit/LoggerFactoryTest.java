package com.acme.unit;

import com.acme.states.LoggerState;
import com.acme.states.LoggerStateFactory;
import com.jet.present.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerFactoryTest {

    private LoggerStateFactory factory;
    private ConsolePrinter printer;


    @Before
    public void initLogger(){
        printer = mock(ConsolePrinter.class);
        factory = new LoggerStateFactory(printer);
    }

    @Test
    public void shouldStringBufferPrintedInsideStringState(){
        LoggerState state = factory.setToStringState(new LoggerState(printer));
        factory.setToStringState(state);
        factory.setToStringState(state);
        factory.setToStringState(state);
        verify(printer,times(0)).print(anyString());
    }


}
