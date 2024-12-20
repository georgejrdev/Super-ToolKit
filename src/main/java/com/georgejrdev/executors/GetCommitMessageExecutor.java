package com.georgejrdev.executors;

import com.georgejrdev.utils.ia.IARequest;
import com.georgejrdev.utils.ia.IAResponse;

public class GetCommitMessageExecutor {

    private IARequest requestIA;

    public GetCommitMessageExecutor(IARequest requestIA) {
        this.requestIA = requestIA;
    }
    
    public boolean getCommitMessage(String description) {
        
        final String BASE_IA_PROMPT = "Retorne uma messagem de commit com base na descrição. A messagem deve ser escrita em inglês e ser bem formatada. A descrição deve ser escrita em inglês e ser bem formatada. Você deve retornar apenas a messagem de commit, sem nada mais. Descrição: ";
        final String FULL_IA_PROMPT = BASE_IA_PROMPT + description;

        IAResponse response = requestIA.request(FULL_IA_PROMPT);

        if (response.isSuccess()) {
            System.out.println(response.getContent());
            return response.isSuccess();

        } else {
            System.out.println(response.getContent());
            return response.isSuccess();
        }
    }
}
