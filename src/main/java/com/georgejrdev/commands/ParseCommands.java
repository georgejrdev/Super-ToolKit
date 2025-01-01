package com.georgejrdev.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ParseExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;
import static com.georgejrdev.DefaultValues.PROGRAM_PATH;

public class ParseCommands implements Commands{
    
    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String[] args){

        logger.info("Executing command " + args[0] + ". All arguments: " + Arrays.toString(args));
        
        try {
            OptionsValidation.expectedQuantityOfParameters(args, 2, 3);
            
            if (args.length == 3){
                OptionsValidation.optionIsAvailable(args[0],args[1]);
            }

            OptionsValidation.parameterIsAvailable(args[args.length - 1]);
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
        
        catch (InvalidOptionCommand e){
            System.out.println("Invalid option " + args[1] + " on command " + args[0] + ". You can see more details in the log file located at " + PROGRAM_PATH);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Invalid option " + args[1] + " on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());

            Helper.invalidOption(args[0], args[1]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Argument " + args[args.length - 1] + " is not valid. You can see more details in the log file located at " + PROGRAM_PATH);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Argument " + args[args.length - 1] + " is not valid on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());
            
            Helper.listCommands(args[0]);
            return;
        }

        final String HOT_RELOAD = (args.length == 3) ? args[1] : null;
        final String SRC_FILE_PATH = args[args.length - 1];

        ParseExecutor parseExecutor = new ParseExecutor(SRC_FILE_PATH);

        if (HOT_RELOAD == null){
            parseExecutor.parseToHtml();
            
        } else {
            parseExecutor.parseToHtmlWithHotReload();
        }
    }
}
