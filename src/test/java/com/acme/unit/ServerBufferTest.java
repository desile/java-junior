package com.acme.unit;

import com.acme.exceptions.PrinterException;
import com.acme.server.ServerMessageBuffer;
import com.jet.present.ConsolePrinter;
import com.jet.present.Printers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by DeSile on 11.11.2015.
 */
public class ServerBufferTest {

    private ServerMessageBuffer smb;

    @Before
    public void init(){
        smb = new ServerMessageBuffer();
    }

    @Test
    public void ShouldFullBufferFlushAndPrintMessage(){
        for(int i = 0; i < ServerMessageBuffer.MAX_SIZE_OF_MSG_BUFFER-1; i++){
            smb.add("Test message");
        }
        boolean shouldFlush = smb.add("50th message");
        assertEquals(shouldFlush, true);
    }

    @Test
    public void ShouldNotFullBufferNotFlushMessages(){
        for(int i = 0; i < ServerMessageBuffer.MAX_SIZE_OF_MSG_BUFFER-2; i++){
            smb.add("Test message");
        }
        boolean shouldFlush = smb.add("49th message");
        assertEquals(shouldFlush, false);
    }

    @Test
    public void ShouldErrorMessagesGetFromBufferFirst() throws PrinterException {
        for(int i = 0; i < 5; i++){
            if(i%2 == 0) smb.add("[ERROR]:Test message");
            else smb.add("[MSG]:Test message");
        }
        Printers p = mock(Printers.class);
        smb.flush(p);
        InOrder order = inOrder(p);
        order.verify(p,times(3)).print("[ERROR]:Test message");
        order.verify(p,times(2)).print("[MSG]:Test message");
    }

}
