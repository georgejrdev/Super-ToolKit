package com.georgejrdev;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.georgejrdev.core.Translate;


public class TestTranslate {

    private Translate translator;

    @Before
    public void setUp(){
        translator = new Translate();
    }

    @Test
    public void testCorrectTranslation(){
        String result = translator.translate("Olá", "en");
        assertEquals("Hello",result);
    }

    @Test
    public void testEmptyInput(){
        String result = translator.translate("","en");
        assertEquals("java.io.FileNotFoundException: https://translate-api-0unn.onrender.com/getTranslation/|en",result);
    }

    @Test
    public void testInvalidLanguage(){
        String result = translator.translate("Hello","xx");
        assertEquals("java.io.IOException: Server returned HTTP response code: 500 for URL: https://translate-api-0unn.onrender.com/getTranslation/Hello|xx",result);
    }
}