package com.georgejrdev.commands;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;


public class ToDoCommands implements Commands{
    
    private final Helper helper;
    private final OptionsValidation optionsValidation;

    public ToDoCommands(Helper helper){
        this.helper = helper;
        this.optionsValidation = new OptionsValidation();
    }


    public void run(String[] args){

        try{
            this.optionsValidation.expectedQuantityOfParameters(args, 3);
            this.optionsValidation.optionIsAvailable(args[0],args[1]);
            this.optionsValidation.parameterIsAvailable(args[2]);
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
            System.out.println("Argument " + args[2] + " is not valid");
            helper.listCommands(args[0]);
            return;
        }

        final String OPTION = args[1];
        final String PARAMETER = args[2];

        // Logic
    }
}