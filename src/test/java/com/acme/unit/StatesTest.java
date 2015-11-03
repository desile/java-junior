package com.acme.unit;

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

    @Before
    public void setPrinterMock(){
        printer = mock(ConsolePrinter.class);
    }

    @Test
    public void shouldStringAddedToStringBuffer(){
        LoggerState state = new LoggerStringState(printer);
        state.toBuffer("Kek");
        state.printBuffer();
        verify(printer).print("string: Kek");
    }

    @Test
    public void shouldStringStateSummingStrings(){
        LoggerState state = new LoggerStringState(printer);
        state.toBuffer("Kek");
        state.toBuffer("Kek");
        state.printBuffer();
        verify(printer).print("string: Kek (x2)");
    }

    @Test
    public void shouldSumStateSummingNumbers(){
        LoggerState state = new LoggerSumState(printer);
        state.toBuffer(50);
        state.toBuffer(22);
        state.toBuffer(-2);
        state.printBuffer();
        verify(printer).print("primitive: 70");
    }

}
