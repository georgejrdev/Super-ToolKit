package com.georgejrdev.commands;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

import com.georgejrdev.commands.interfaces.Commands;
import com.georgejrdev.executors.ToDoExecutor;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.validations.OptionsValidation;
import com.georgejrdev.utils.helper.AppLogger;
import static com.georgejrdev.DefaultValues.PROGRAM_PATH;

public class ToDoCommands implements Commands{

    private static final Logger logger = AppLogger.getLogger();

    @Override
    public void run(String[] args){

        logger.info("Executing command " + args[0] + ". All arguments: " + args.toString());

        try{
            OptionsValidation.expectedQuantityOfParameters(args, 2, 3);
            OptionsValidation.optionIsAvailable(args[0],args[1]);
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