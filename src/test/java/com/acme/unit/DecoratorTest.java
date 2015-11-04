package com.acme.unit;

import com.acme.LoggerDecorator;
import com.acme.LoggerPrefixDecorator;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by DeSile on 04.11.2015.
 */
public class DecoratorTest {

    HashMap<String,String> decor;

    @Before
    public void initDecorMap(){
        decor = new HashMap<>();
        decor.put("TEST","test decoration");
    }

    @Test
    public void shouldDecorMapInPrefixDecoratorChangeOutput(){
        LoggerPrefixDecorator decorator = new LoggerPrefixDecorator(decor);
        assertEquals(decorator.decorate("TEST","some string"),"test decoration: some string");
    }

    @Test
    public void shouldDecoratorReactOnDecorMapChanging(){
        LoggerDecorator decorator = new LoggerPrefixDecorator(decor);
        assertEquals(decorator.decorate("TEST","kek"),"test decoration: kek");
        decor.replace("TEST","new decoration");
        assertEquals(decorator.decorate("TEST","kek"),"new decoration: kek");
    }

}
