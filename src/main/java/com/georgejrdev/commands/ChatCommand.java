package com.georgejrdev.commands;

import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ChatExecutor;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.AppLogger;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.ia.GeminiRequest;
import com.georgejrdev.utils.validations.OptionsValidation;

public class ChatCommand implements Commands{
    
    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String[] args){

        try {
            OptionsValidation.expectedQuantityOfParameters(args, 2);
            OptionsValidation.parameterIsAvailable(args[1]);
        }

        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            logger.severe("Unexpected number of parameters on command " + args[0]);
            Helper.listCommands(args[0]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Argument " + args[1] + " is not valid");
            logger.severe("Argument " + args[1] + " is not valid on command " + args[0]);
            Helper.listCommands(args[0]);
            return;
        }

        final String ANSWER = args[1];
        ChatExecutor chatExecutor = new ChatExecutor(new GeminiRequest());
        chatExecutor.getIAResponse(ANSWER);
    }
}