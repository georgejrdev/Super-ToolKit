package com.georgejrdev.commands;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.GetCommitMessageExecutor;
import com.georgejrdev.utils.exceptions.AIKeyNotFound;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.Helper;

public class GetCommitMessageCommand implements Commands{
    
    public void run(String[] args){

        try {
            OptionsValidation.expectedQuantityOfParameters(args, 2);
            OptionsValidation.parameterIsAvailable(args[1]);
            OptionsValidation.isAIKeyConfigured();
        }

        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            Helper.listCommands(args[0]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Argument " + args[1] + " is not valid");
            Helper.listCommands(args[0]);
            return;
        }

        catch (AIKeyNotFound e){
            System.out.println("AI Key not found. Create an environment variable named STK_GEMINI_API_KEY with the value of your gemini-1.5-flash API access key. It's free. Link: https://ai.google.dev/pricing?hl=pt-br#1_5flash");
            return;
        }

        final String COMMIT_DESCRIPTION = args[1];

        GetCommitMessageExecutor.getCommitMessage(COMMIT_DESCRIPTION);
    }
}