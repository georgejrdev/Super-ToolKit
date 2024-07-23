package com.georgejrdev;

import com.georgejrdev.auxiliar.processing.interfaces.Help;
import com.georgejrdev.auxiliar.processing.HelpImpl; 
import com.georgejrdev.auxiliar.cli.interfaces.Command;
import com.georgejrdev.auxiliar.cli.CommandFactory;


public class Main {

    public static void main(String[] args) {
        Help help = new HelpImpl();

        if (args.length == 0) {
            System.out.println("Parameters Required");
            help.showCommands();
            return;
        }

        String choice = args[0];

        CommandFactory commandFactory = new CommandFactory();
        Command command = commandFactory.getCommand(choice);

        if (command != null) {
            command.execute(args);
            
        } else {
            System.out.println(choice + " does not correspond to any function");
            help.showCommands();
        }
    }
}