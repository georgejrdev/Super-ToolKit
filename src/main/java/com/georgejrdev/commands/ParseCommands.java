package com.georgejrdev.commands;

import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ParseExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;

public class ParseCommands implements Commands{
    
    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String[] args){
        
        try {
            OptionsValidation.expectedQuantityOfParameters(args, 2, 3);
            
            if (args.length == 3){
                OptionsValidation.optionIsAvailable(args[0],args[1]);
            }

            OptionsValidation.parameterIsAvailable(args[args.length - 1]);
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
            System.out.println("Argument " + args[args.length - 1] + " is not valid");
            logger.severe("Argument " + args[args.length - 1] + " is not valid on command " + args[0]);
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
