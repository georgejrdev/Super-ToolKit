package com.georgejrdev.commands;

import static com.georgejrdev.DefaultValues.PROGRAM_PATH;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.RamViewerExecutor;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.AppLogger;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;

public class RamViewerCommand implements Commands{
    
    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String[] args) {

        logger.info("Executing command " + args[0] + ". All arguments: " + Arrays.toString(args));

        try {
            OptionsValidation.expectedQuantityOfParameters(args, 1);   
        }

        catch (UnexpectedNumberOfParameters e) {
            System.out.println("Unexpected number of parameters. You can see more details in the log file located at " + PROGRAM_PATH);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Unexpected number of parameters on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());

            Helper.listCommands(args[0]);
            return;
        }

        RamViewerExecutor.view();
    }
}