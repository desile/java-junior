package com.acme;

import java.util.HashMap;

/**
 * Created by DeSile on 03.11.2015.
 */
public interface LoggerDecorator {

    String decorate(String src, String msg);

    void setDecor(HashMap<String,String> decor);

}
