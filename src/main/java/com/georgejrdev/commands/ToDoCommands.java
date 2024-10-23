package com.georgejrdev.commands;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ToDoExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;

public class ToDoCommands implements Commands{

    public void run(String[] args){

        try{
            OptionsValidation.expectedQuantityOfParameters(args, 2, 3);
            OptionsValidation.optionIsAvailable(args[0],args[1]);
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

        final String OPTION = args[1];
        final String PARAMETER = (args.length == 3) ? args[2] : null;

        ToDoExecutor toDoExecutor = new ToDoExecutor();

        switch (OPTION){
            case "add":
                toDoExecutor.createNewTask(PARAMETER);
                break;
            
            case "remove":
                toDoExecutor.deleteTask(Integer.parseInt(PARAMETER));
                break;

            case "check":
                toDoExecutor.checkTask(Integer.parseInt(PARAMETER), false);
                break;

            case "uncheck":
                toDoExecutor.checkTask(Integer.parseInt(PARAMETER), true);
                break;
            
            case "list":
                toDoExecutor.showTasks();
                break;

            default:
                Helper.invalidOption(args[0], OPTION);;
                break;
        }
    }
}