package com.georgejrdev;

import com.georgejrdev.commands.ParseCommands;
import com.georgejrdev.commands.ToDoCommands;
import com.georgejrdev.commands.TranslateCommands;
import com.georgejrdev.utils.helper.Helper;

public class SuperToolKit {

    public static void main(String[] args){


        if (args.length == 0){
            Helper.listCommands();
            return;
        }

        String command = args[0].toLowerCase();

        switch (command){
            case "help", "-h":
                if (args.length == 2){
                    Helper.listCommands(args[1]);
                    break;
                }

                Helper.listCommands();
                break;

            case "translate", "-t":
                TranslateCommands translateCommands = new TranslateCommands();
                translateCommands.run(args);
                break;

            case "todo", "-td":
                ToDoCommands toDoCommands = new ToDoCommands();
                toDoCommands.run(args);
                break;

            case "parse", "-p":
                ParseCommands parseCommands = new ParseCommands();
                parseCommands.run(args);
                break;

            default:
                Helper.nonExistentCommand(command);
                break;
        }
    }
}