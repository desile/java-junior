package com.acme.states;

import static com.jet.present.Printer.print;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerCharState extends LoggerState {

    private char buffer;

    @Override
    public void printBuffer() {
        print("char: " + buffer);
    }

    @Override
    public void toBuffer(char msg){
        this.buffer = msg;
    }
}
