package com.georgejrdev;

import com.georgejrdev.commands.ParseCommands;
import com.georgejrdev.utils.helper.Helper;


public class Main {
    public static void main(String[] args){

        Helper helper = new Helper();

        if (args.length == 0){
            helper.listCommands();
            return;
        }

        String command = args[0];
        command.toLowerCase();

        switch (command){
            case "help", "-h":
                if (args.length == 2){
                    helper.listCommands(args[1]);
                    break;
                }

                helper.listCommands();
                break;

            case "translate", "-t":
                break;

            case "todo", "-td":
                break;

            case "parse", "-p":
                ParseCommands parseCommands = new ParseCommands(helper);
                parseCommands.run(args);
                break;

            default:
                helper.nonExistentCommand(command);
                break;
        }
    }
}