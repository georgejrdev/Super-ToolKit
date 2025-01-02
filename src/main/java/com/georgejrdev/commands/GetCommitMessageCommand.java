package com.georgejrdev.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.GetCommitMessageExecutor;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.ia.GeminiRequest;
import com.georgejrdev.utils.helper.AppLogger;

import static com.georgejrdev.DefaultValues.*;

public class GetCommitMessageCommand implements Commands{
    
    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String[] args){

        logger.info("Executing command " + args[0] + ". All arguments: " + Arrays.toString(args));

        try {
            OptionsValidation.expectedQuantityOfParameters(args, 2);
            OptionsValidation.parameterIsAvailable(args[1]);
        }

        catch (UnexpectedNumberOfParameters e){
            System.out.println(ANSI_RED+"Unexpected number of parameters. You can see more details in the log file located at " + PROGRAM_PATH+ ANSI_RESET);
            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Unexpected number of parameters on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());

            Helper.listCommands(args[0]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println(ANSI_RED+"Argument " + args[1] + " is not valid. You can see more details in the log file located at " + PROGRAM_PATH + ANSI_RESET);
            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Argument " + args[1] + " is not valid on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());
            
            Helper.listCommands(args[0]);
            return;
        }

        final String COMMIT_DESCRIPTION = args[1];
        GetCommitMessageExecutor getCommitMessageExecutor = new GetCommitMessageExecutor(new GeminiRequest());
        getCommitMessageExecutor.getCommitMessage(COMMIT_DESCRIPTION);
    }
}