package com.acme.unit;

import com.acme.states.LoggerState;
import com.acme.LoggerStateFactory;
import com.jet.present.ConsolePrinter;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerFactoryTest {

    private LoggerStateFactory factory;
    private LoggerState state;
    private ConsolePrinter printer;


    @Before
    public void initLogger(){
        printer = mock(ConsolePrinter.class);

        factory = new LoggerStateFactory(printer);
        state = factory.setToComState(null);
    }

    @Test
    public void shouldStringBufferNotPrintedInsideStringState(){
        state = factory.setToStringState(state);//0
        state = factory.setToStringState(state);//0
        state = factory.setToStringState(state);//0
        verify(printer,times(0)).print(anyString());
    }

    @Test
    public void shouldIntegerBufferNotPrintedInsideSumState(){
        state = factory.setToSumState(state);//0
        state = factory.setToSumState(state);//0
        state = factory.setToSumState(state);//0
        verify(printer,times(0)).print(anyString());
    }

    @Test
    public void shouldStatesSwitchCorrectly(){
        state = factory.setToBoolState(state);//0: print not called because factory in ComState (com state hasnot print)
        state = factory.setToBoolState(state);//1
        state = factory.setToCharState(state);//2
        state = factory.setToObjState(state);//3
        state = factory.setToStringState(state);//4
        state = factory.setToSumState(state);//5
        state = factory.setToComState(state);//6
        verify(printer,times(6)).print(anyString());
    }

    @Test
    public void shouldLogDifferentTypesInOneSession(){
        state = factory.setToBoolState(state);
        state.toBuffer(true);

        state = factory.setToCharState(state);
        state.toBuffer('a');

        Object obj = new Object();
        state = factory.setToObjState(state);
        state.toBuffer(obj);

        state = factory.setToSumState(state);
        state.toBuffer(10);

        state = factory.setToSumState(state);
        state.toBuffer(20);


        state = factory.setToComState(state);


        verify(printer).print("primitive: true");
        verify(printer).print("char: a");
        verify(printer).print("reference: " + obj);
        verify(printer).print("primitive: 30");
    }

    @Test
    public void shouldDifferentStringSummingInOneSession(){
        state = factory.setToStringState(state);
        state.toBuffer("stringToLog");

        state = factory.setToStringState(state);
        state.toBuffer("stringToLog");

        state = factory.setToStringState(state);
        state.toBuffer("anotherStringToLog");

        state = factory.setToStringState(state);
        state.toBuffer("anotherStringToLog");

        state = factory.setToStringState(state);
        state.toBuffer("anotherStringToLog");

        state = factory.setToComState(state);

        verify(printer).print("string: stringToLog (x2)");
        verify(printer).print("string: anotherStringToLog (x3)");
    }

    /*
    @Test
    public void shouldNullThrowException(){
        state = null;
        state.printBuffer();
        state.toBuffer(4);
    }
    */
}
