package com.georgejrdev.auxiliar.processing;

import com.georgejrdev.auxiliar.processing.interfaces.Request;
import com.georgejrdev.auxiliar.processing.interfaces.Translate;


public class TranslateImpl implements Translate{

    private Request request;
    private final String URL_API = "https://translate-api-0unn.onrender.com/getTranslation/";
    private final String SEPARATOR = "|";


    public TranslateImpl(Request request){
        this.request = request;
    }


    @Override
    public String translate(String text, String lang){
        final String REQUEST_URL = URL_API + text + SEPARATOR + lang;

        return this.request.getResultRequest(REQUEST_URL,"GET");
    }
}
