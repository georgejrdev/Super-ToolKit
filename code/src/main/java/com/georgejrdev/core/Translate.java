package com.georgejrdev.core;

import com.georgejrdev.core.interfaces.TranslateInterface;


public class Translate implements TranslateInterface {
    
    private final String URL_API = "https://translate-api-0unn.onrender.com/getTranslation/";
    private final String SEPARATOR = "|";


    @Override
    public String translate(String text, String lang){
        Request request = new Request();
        final String REQUEST_URL = URL_API + text + SEPARATOR + lang;

        return request.getResultRequest(REQUEST_URL,"GET");
    }
}