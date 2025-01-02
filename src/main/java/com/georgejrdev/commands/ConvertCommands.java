package com.georgejrdev.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ConvertExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;

import static com.georgejrdev.DefaultValues.*;

public class ConvertCommands implements Commands{

    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String args[]){

        logger.info("Executing command " + args[0] + ". All arguments: " + Arrays.toString(args));

        try{
            OptionsValidation.expectedQuantityOfParameters(args, 4);
            OptionsValidation.optionIsAvailable(args[0], args[1]);
            OptionsValidation.parameterIsAvailable(args[2]);
            OptionsValidation.parameterIsAvailable(args[3]);
        }

        catch (UnexpectedNumberOfParameters e){
            System.out.println(ANSI_RED+"Unexpected number of parameters. You can see more details in the log file located at " + PROGRAM_PATH + ANSI_RESET);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Unexpected number of parameters on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());

            Helper.listCommands(args[0]);
            return;
        }

        catch (InvalidOptionCommand e){
            System.out.println(ANSI_RED+"Invalid option " + args[1] + " on command " + args[0] + ". You can see more details in the log file located at " + PROGRAM_PATH + ANSI_RESET);
            
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Invalid option " + args[1] + " on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());

            Helper.invalidOption(args[0], args[1]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println(ANSI_RED+"Arguments " + args[2] + " or " + args[3] + " are not valid. You can see more details in the log file located at " + PROGRAM_PATH+ ANSI_RESET);

            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            logger.warning("Arguments " + args[2] + " or " + args[3] + " are not valid on command " + args[0]);
            logger.fine("Stack trace for the error:\n" + sw.toString());
            
            Helper.listCommands(args[0]);
            return;
        }

        final String OPTION = args[1];
        final String PATH = args[2];
        final String NEW_TYPE = args[3];

        ConvertExecutor convert = new ConvertExecutor();

        switch (OPTION) {
            case "image":
                convert.convertImage(PATH, NEW_TYPE);
                break;
        
            case "video":
                convert.convertVideo(PATH, NEW_TYPE);
                break;
            
            case "audio":
                convert.convertAudio(PATH, NEW_TYPE);
                break;

            default:
                Helper.invalidOption(args[0], OPTION);
                break;
        }
    }
}