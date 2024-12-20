package com.georgejrdev.commands;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.GetCommitMessageExecutor;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.ia.GeminiRequest;

public class GetCommitMessageCommand implements Commands{
    
    public void run(String[] args){

        try {
            OptionsValidation.expectedQuantityOfParameters(args, 2);
            OptionsValidation.parameterIsAvailable(args[1]);
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

        final String COMMIT_DESCRIPTION = args[1];
        GetCommitMessageExecutor getCommitMessageExecutor = new GetCommitMessageExecutor(new GeminiRequest());
        getCommitMessageExecutor.getCommitMessage(COMMIT_DESCRIPTION);
    }
}