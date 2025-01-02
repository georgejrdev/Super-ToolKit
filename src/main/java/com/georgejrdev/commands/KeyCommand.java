package com.georgejrdev.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.KeyExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;

import static com.georgejrdev.DefaultValues.*;

public class KeyCommand implements Commands{

    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String[] args){

        logger.info("Executing command " + args[0] + ". All arguments: " + Arrays.toString(args));

        try{
            OptionsValidation.expectedQuantityOfParameters(args, new int[]{2,3,4});
            OptionsValidation.optionIsAvailable(args[0], new String[]{args[1]});
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

        final String OPTION = args[1];

        KeyExecutor keyExecutor = new KeyExecutor();

        switch (OPTION){
            case "add":
                if (args.length != 4){
                    Helper.listCommands(args[0]);;
                    return;
                }
                keyExecutor.createNewKey(args[2], args[3]);;
                break;
            
            case "remove":
                if (args.length != 3){
                    Helper.listCommands(args[0]);;
                    return;
                }
                keyExecutor.deleteKey(args[2]);;
                break;

            case "list":
                if (args.length == 3){
                    keyExecutor.showKey(args[2]);
                }
                else{
                    keyExecutor.showKeys();
                }
                break;

            default:
                Helper.invalidOption(args[0], OPTION);;
                break;
        }
    }
}