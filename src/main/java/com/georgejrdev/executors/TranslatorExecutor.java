package com.georgejrdev.executors;

import com.georgejrdev.utils.ia.GeminiRequest;
import com.georgejrdev.utils.ia.IARequest;
import com.georgejrdev.utils.ia.IAResponse;

public class TranslatorExecutor {
    
    private IARequest request;

    public TranslatorExecutor(GeminiRequest request){
        this.request = request;
    }

    public boolean translate(String text, String language){
        final String PROMPT = "Traduza o texto '" + text + "' para o idioma '" + language + "'. Responda apenas com o texto traduzido, sem mais nada.";

        IAResponse response = request.request(PROMPT);

        if (response.isSuccess()){
            System.out.println(response.getContent());
            return response.isSuccess();
            
        } else {
            System.out.println(response.getContent());
            return response.isSuccess();
        }
    }
}