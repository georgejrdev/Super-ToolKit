package com.georgejrdev.commands;

import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ConvertExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;

public class ConvertCommands implements Commands{

    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String args[]){

        try{
            OptionsValidation.expectedQuantityOfParameters(args, 4);
            OptionsValidation.optionIsAvailable(args[0], args[1]);
            OptionsValidation.parameterIsAvailable(args[2]);
            OptionsValidation.parameterIsAvailable(args[3]);
        }

        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            logger.severe("Unexpected number of parameters on command " + args[0]);
            Helper.listCommands(args[0]);
            return;
        }

        catch (InvalidOptionCommand e){
            logger.severe("Invalid option " + args[1] + " on command " + args[0]);
            Helper.invalidOption(args[0], args[1]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Arguments " + args[2] + " or " + args[3] + " are not valid");
            logger.severe("Arguments " + args[2] + " or " + args[3] + " are not valid on command " + args[0]);
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