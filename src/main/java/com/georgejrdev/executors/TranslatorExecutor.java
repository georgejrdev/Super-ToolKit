package com.georgejrdev.executors;

import java.util.logging.Logger;

import com.georgejrdev.utils.ia.GeminiRequest;
import com.georgejrdev.utils.ia.IARequest;
import com.georgejrdev.utils.ia.IAResponse;
import com.georgejrdev.utils.helper.AppLogger;

public class TranslatorExecutor {
    
    private IARequest request;
    private static final Logger logger = AppLogger.getLogger();

    public TranslatorExecutor(GeminiRequest request){
        this.request = request;
    }

    public boolean translate(String text, String language){
        logger.info("Translating text - Text: "+text+" Language: "+language);

        final String PROMPT = "Traduza o texto '" + text + "' para o idioma '" + language + "'. Responda apenas com o texto traduzido, sem mais nada.";

        IAResponse response = request.request(PROMPT);

        if (response.isSuccess()){
            System.out.println(response.getContent());
            logger.info("Success! Text: "+text+" Language: "+language+" Response: "+response.getContent());
            return response.isSuccess();
            
        } else {
            System.out.println(response.getContent());
            logger.severe("Error! Text: "+text+" Language: "+language+" Response: "+response.getContent());
            return response.isSuccess();
        }
    }
}