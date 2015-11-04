package com.acme.unit;

import com.acme.LoggerDecorator;
import com.acme.LoggerPrefixDecorator;
import com.acme.states.*;
import com.jet.present.ConsolePrinter;
import com.jet.present.Printable;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by DeSile on 03.11.2015.
 */
public class StatesTest {

    ConsolePrinter printer;
    LoggerPrefixDecorator decorator;

    @Before
    public void setPrinterMock(){
        printer = mock(ConsolePrinter.class);
    }

    @Before
    public void setDecorator(){
        decorator = mock(LoggerPrefixDecorator.class);
    }

    @Test
    public void shouldStringAddedToStringBuffer(){
        LoggerState state = new LoggerStringState(printer,decorator);
        state.toBuffer("Kek");
        state.printBuffer();
        verify(decorator).decorate("STRING","Kek");
    }

    @Test
    public void shouldStringStateSummingStrings(){
        LoggerState state = new LoggerStringState(printer,decorator);
        state.toBuffer("Kek");
        state.toBuffer("Kek");
        state.printBuffer();
        verify(decorator).decorate("STRING","Kek (x2)");
    }


    @Test
    public void shouldStringStateCallPrintWhenNextStringNotEqualsToPrevious(){
        LoggerState state = new LoggerStringState(printer,decorator);
        state.toBuffer("First");
        state.toBuffer("Second");
        state.printBuffer();
        verify(decorator).decorate("STRING","First");
        verify(decorator).decorate("STRING", "Second");
    }

    @Test
    public void shouldSumStateSummingNumbers(){
        LoggerState state = new LoggerSumState(printer,decorator);
        state.toBuffer(50);
        state.toBuffer(22);
        state.toBuffer(-2);
        state.printBuffer();
        verify(decorator).decorate("INT","70");
    }


    @Test
    public void shouldSumStatePreventOverflowing(){
        LoggerState state = new LoggerSumState(printer,decorator);
        state.toBuffer(Integer.MAX_VALUE);
        state.toBuffer(10);
        state.toBuffer(Integer.MIN_VALUE);
        state.toBuffer(-100);
        state.printBuffer();
        verify(decorator).decorate("INT", Integer.MAX_VALUE + "");
        verify(decorator).decorate("INT", 10+Integer.MIN_VALUE + "");
        verify(decorator).decorate("INT", "-100");
    }


    @Test
    public void shouldBooleanAddedToBooleanBuffer(){
        LoggerState state = new LoggerBoolState(printer,decorator);
        state.toBuffer(true);
        state.printBuffer();
        state.toBuffer(false);
        state.printBuffer();
        verify(decorator).decorate("BOOL", "true");
        verify(decorator).decorate("BOOL", "false");
    }

    @Test
    public void shouldObjectAddedToObjectStateBuffer(){
        LoggerState state = new LoggerObjState(printer,decorator);
        Object obj = new Object();
        state.toBuffer(obj);
        state.printBuffer();
        verify(decorator).decorate("OBJ",obj.toString());
    }

    @Test
    public void shouldCharAddedToCharStateBuffer(){
        LoggerState state = new LoggerCharState(printer,decorator);
        state.toBuffer('a');
        state.printBuffer();
        verify(decorator).decorate("CHAR","a");
    }

}
