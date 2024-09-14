package com.georgejrdev.commands;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;


public class ToDoCommands implements Commands{
    
    public ToDoCommands(){}


    public void run(String[] args){

        try{
            OptionsValidation.expectedQuantityOfParameters(args, 3);
            OptionsValidation.optionIsAvailable(args[0],args[1]);
            OptionsValidation.parameterIsAvailable(args[2]);
        }
        
        catch (UnexpectedNumberOfParameters e){
            System.out.println("Unexpected number of parameters");
            Helper.listCommands(args[0]);
            return;
        } 

        catch (InvalidOptionCommand e){
            Helper.invalidOption(args[0], args[1]);
            return;
        }

        catch (IllegalArgumentException e){
            System.out.println("Argument " + args[2] + " is not valid");
            Helper.listCommands(args[0]);
            return;
        }

        //final String OPTION = args[1];
        //final String PARAMETER = args[2];

        // Logic
    }
}