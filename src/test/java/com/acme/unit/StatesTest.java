package com.acme.unit;

import com.acme.LoggerDecorator;
import com.acme.LoggerPrefixDecorator;
import com.acme.states.LoggerState;
import com.acme.states.LoggerStringState;
import com.acme.states.LoggerSumState;
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
        decorator = mock(LoggerPrefixDecorator.class);

    }

    @Test
    public void shouldStringAddedToStringBuffer(){
        LoggerState state = new LoggerStringState(printer);
        stub(decorator.decorate("STRING",anyString())).toReturn("string: Kek");
        state.toBuffer("Kek");
        state.printBuffer(decorator);
        verify(printer).print("string: Kek");
    }

    @Test
    public void shouldStringStateSummingStrings(){
        LoggerState state = new LoggerStringState(printer);
        stub(decorator.decorate("STRING",anyString())).toReturn("string: Kek (x2)");
        state.toBuffer("Kek");
        state.toBuffer("Kek");
        state.printBuffer(decorator);
        verify(printer).print("string: Kek (x2)");
    }

    @Test
    public void shouldSumStateSummingNumbers(){
        LoggerState state = new LoggerSumState(printer);
        state.toBuffer(50);
        state.toBuffer(22);
        state.toBuffer(-2);
        state.printBuffer(decorator);
        verify(printer).print("primitive: 70");
    }

}
