package com.acme;

import java.util.Map;

/**
 * Created by DeSile on 03.11.2015.
 */
public class LoggerPrefixDecorator implements LoggerDecorator {

    private Map<String,String> decor;

    public LoggerPrefixDecorator(Map<String,String> decor){
        this.decor = decor;
    }

    @Override
    public String decorate(String src, String msg) {
        return decor.get(src) + ": " + msg;
    }

    @Override
    public void setDecor(Map<String, String> decor) {
        this.decor = decor;
    }


}
