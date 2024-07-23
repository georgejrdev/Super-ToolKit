package com.georgejrdev.auxiliar.cli.commands;

import com.georgejrdev.auxiliar.cli.interfaces.Command;
import com.georgejrdev.auxiliar.processing.interfaces.Help;


public class HelpCommand implements Command{
    private Help help;


    public HelpCommand(Help help){
        this.help = help;
    }


    @Override
    public void execute(String[] args) {
        help.showCommands();
    }    
}
