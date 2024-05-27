package com.georgejrdev;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.georgejrdev.core.Request;


public class TestRequest {

    private Request request;

    @Before
    public void setUp(){
        request = new Request();
    }

    @Test
    public void testRequestValidUrl(){
        String result = request.getResultRequest("https://translate-api-0unn.onrender.com/getTranslation/Olá|en", "GET");
        assertEquals("Hello",result);
    }
}
