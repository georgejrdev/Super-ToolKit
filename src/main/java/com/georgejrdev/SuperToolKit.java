package com.georgejrdev;

import com.georgejrdev.commands.ConvertCommands;
import com.georgejrdev.commands.GetCommitMessageCommand;
import com.georgejrdev.commands.ParseCommands;
import com.georgejrdev.commands.ToDoCommands;
import com.georgejrdev.commands.TranslateCommands;
import com.georgejrdev.utils.helper.Helper;

public class SuperToolKit {

    static public final String VERSION = "3.3.1";

    public static void main(String[] args){

        if (args.length == 0){
            Helper.listCommands();
            return;
        }

        String command = args[0].toLowerCase();

        switch (command){
            case "help":
                if (args.length == 2){
                    Helper.listCommands(args[1]);
                    break;
                }

                Helper.listCommands();
                break;
            
            case "version":
                System.out.println("v" + VERSION);
                break;

            case "translate":
                TranslateCommands translateCommands = new TranslateCommands();
                translateCommands.run(args);
                break;

            case "todo":
                ToDoCommands toDoCommands = new ToDoCommands();
                toDoCommands.run(args);
                break;

            case "parse":
                ParseCommands parseCommands = new ParseCommands();
                parseCommands.run(args);
                break;

            case "convert":
                ConvertCommands convertCommands = new ConvertCommands();
                convertCommands.run(args);
                break;

            case "commit":
                GetCommitMessageCommand getCommitMessageCommand = new GetCommitMessageCommand();
                getCommitMessageCommand.run(args);
                break;

            default:
                Helper.nonExistentCommand(command);
                break;
        }
    }
}