package com.georgejrdev;

import static com.georgejrdev.DefaultValues.*;

import java.io.File;

import com.georgejrdev.commands.ChatCommand;
import com.georgejrdev.commands.ConvertCommands;
import com.georgejrdev.commands.GetCommitMessageCommand;
import com.georgejrdev.commands.ParseCommands;
import com.georgejrdev.commands.ToDoCommands;
import com.georgejrdev.commands.TranslateCommands;
import com.georgejrdev.utils.helper.Configurator;
import com.georgejrdev.utils.helper.Helper;
import com.georgejrdev.utils.helper.Updater;

public class SuperToolKit {

    public static void main(String[] args){

        File dir = new File(DIR_SAVE);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        Updater.updateIsAvailable();

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
                System.out.println("Current version: v" + VERSION);
                break;

            case "update":
                if (args.length == 2 && args[1].equals("force")){
                    Updater.update(true);
                } else {
                    Updater.update(false);
                }
                break;

            case "config":
                Configurator.configureGeminiApiKey();
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

            case "chat":
                ChatCommand chatCommand = new ChatCommand();
                chatCommand.run(args);
                break;

            default:
                Helper.nonExistentCommand(command);
                break;
        }
    }
}