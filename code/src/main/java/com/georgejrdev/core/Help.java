package com.georgejrdev.core;

import java.util.HashMap;
import java.util.Map;

import com.georgejrdev.core.interfaces.HelpInterface;


public class Help implements HelpInterface{
    
    final Map<String,String> COMMAND_SYNTAX = new HashMap<String,String>() {{
        put("-h","tkd -h");
        put("-t","tkd -t 'Text' pt");
        put("-c","tkd -c 'Text'");
    }};


    @Override
    public void showCommands(){
        System.out.println("tkd -h -> Show all commands");
        System.out.println("tkd -t -> Translate a text");
        System.out.println("tkd -c -> Chat with ChatGPT");
    }

    @Override
    public void showCorrectSintax(String command){
        System.out.println("Syntax Error: the correct syntax is -> " + getCommandSyntax(command));
    }

    @Override
    public String getCommandSyntax(String command){
        return COMMAND_SYNTAX.get(command);
    }
}