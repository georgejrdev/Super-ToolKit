package com.georgejrdev.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.TranslatorExecutor;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.ia.GeminiRequest;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;
import static com.georgejrdev.DefaultValues.PROGRAM_PATH;

public class TranslateCommands implements Commands{

    private static final Logger logger = AppLogger.getLogger();
    
    @Override
    public void run(String[] args){

        logger.info("Executing command " + args[0] + ". All arguments: " + Arrays.toString(args));
        
        try {
            OptionsValidation.expectedQuantityOfParameters(args, 3);
            OptionsValidation.parameterIsAvailable(args[1]);
            OptionsValidation.parameterIsAvailable(args[2]);
        } 
        
        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters. You can see more details in the log file located at " + PROGRAM_PATH);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Unexpected number of parameters on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());

            Helper.listCommands(args[0]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Arguments " + args[1] + " or " + args[2] + " are not valid. You can see more details in the log file located at " + PROGRAM_PATH);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Arguments " + args[1] + " or " + args[2] + " are not valid on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());

            Helper.listCommands(args[0]);
            return;
        }

        final String TEXT = args[1];
        final String LANGUAGE = args[2];

        TranslatorExecutor translator = new TranslatorExecutor(new GeminiRequest());
        translator.translate(TEXT, LANGUAGE);
    }
}