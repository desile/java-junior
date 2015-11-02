package com.acme.states;

/**
 * Created by DeSile on 02.11.2015.
 */
public class LoggerCharState extends LoggerState {

    private char buffer;

    @Override
    public void printBuffer() {

    }

    public void toBuffer(char msg){
        this.buffer = msg;
    }
}
