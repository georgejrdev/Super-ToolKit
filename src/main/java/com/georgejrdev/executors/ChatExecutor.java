package com.georgejrdev.executors;

import java.util.logging.Logger;

import com.georgejrdev.utils.helper.AppLogger;
import com.georgejrdev.utils.ia.IARequest;
import com.georgejrdev.utils.ia.IAResponse;

public class ChatExecutor {
    
    private IARequest requestIA;
    private static final Logger logger = AppLogger.getLogger();

    public ChatExecutor(IARequest requestIA) {
        this.requestIA = requestIA;
    }
    
    public boolean getIAResponse(String answer) {
        logger.info("Send answer to IA - Answer: "+answer);

        IAResponse response = requestIA.request(answer);

        if (response.isSuccess()) {
            System.out.println(response.getContent());
            logger.info("Success! Answer: " +answer+ " Response: "+response.getContent());
            return response.isSuccess();

        } else {
            System.out.println(response.getContent());
            logger.severe("Error! Answer: " +answer+ " Response: "+response.getContent());
            return response.isSuccess();
        }
    }
}