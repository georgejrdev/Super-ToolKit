package com.georgejrdev.commands;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ParseExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;


public class ParseCommands implements Commands{
    
    private final Helper helper;
    private final OptionsValidation optionsValidation;

    public ParseCommands(Helper helper){
        this.helper = helper;
        this.optionsValidation = new OptionsValidation();
    }


    public void run(String[] args){
        
        try {
            this.optionsValidation.expectedQuantityOfParameters(args, 2, 3);
            
            if (args.length == 3){
                this.optionsValidation.optionIsAvailable(args[0],args[1]);
            }

            this.optionsValidation.parameterIsAvailable(args[args.length - 1]);
        } 

        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            helper.listCommands(args[0]);
            return;
        }

        catch (InvalidOptionCommand e){
            helper.invalidOption(args[0], args[1]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Argument " + args[args.length - 1] + " is not valid");
            helper.listCommands(args[0]);
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
