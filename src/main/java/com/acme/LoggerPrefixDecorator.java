package com.acme;

import java.util.HashMap;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerPrefixDecorator implements LoggerDecorator {

    private HashMap<String,String> decor;

    public LoggerPrefixDecorator(HashMap<String,String> decor){
        this.decor = decor;
    }

    @Override
    public String decorate(String src, String msg) {
        return decor.get(src) + ": " + msg;
    }

    @Override
    public void setDecor(HashMap<String, String> decor) {
        this.decor = decor;
    }


}
