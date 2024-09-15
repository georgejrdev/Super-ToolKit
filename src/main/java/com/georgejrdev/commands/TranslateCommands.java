package com.georgejrdev.commands;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.TranslatorExecutor;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;


public class TranslateCommands implements Commands{
    
    public TranslateCommands(){}


    public void run(String[] args){
        
        try {
            OptionsValidation.expectedQuantityOfParameters(args, 3);
            OptionsValidation.parameterIsAvailable(args[1]);
            OptionsValidation.parameterIsAvailable(args[2]);
        } 
        
        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            Helper.listCommands(args[0]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Arguments " + args[1] + " or " + args[2] + " are not valid");
            Helper.listCommands(args[0]);
            return;
        }

        final String TEXT = args[1];
        final String LANGUAGE = args[2];

        TranslatorExecutor.translate(TEXT, LANGUAGE);
    }
}
