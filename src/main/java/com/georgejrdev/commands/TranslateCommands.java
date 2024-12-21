package com.georgejrdev.commands;

import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.TranslatorExecutor;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.ia.GeminiRequest;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;

public class TranslateCommands implements Commands{

    private static final Logger logger = AppLogger.getLogger();
    
    public void run(String[] args){
        
        try {
            OptionsValidation.expectedQuantityOfParameters(args, 3);
            OptionsValidation.parameterIsAvailable(args[1]);
            OptionsValidation.parameterIsAvailable(args[2]);
        } 
        
        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            logger.severe("Unexpected number of parameters on command " + args[0]);
            Helper.listCommands(args[0]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Arguments " + args[1] + " or " + args[2] + " are not valid");
            logger.severe("Arguments " + args[1] + " or " + args[2] + " are not valid on command " + args[0]);
            Helper.listCommands(args[0]);
            return;
        }

        final String TEXT = args[1];
        final String LANGUAGE = args[2];

        TranslatorExecutor translator = new TranslatorExecutor(new GeminiRequest());
        translator.translate(TEXT, LANGUAGE);
    }
}
