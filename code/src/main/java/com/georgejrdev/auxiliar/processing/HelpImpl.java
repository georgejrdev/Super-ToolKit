package com.georgejrdev.auxiliar.processing;

import java.util.HashMap;
import java.util.Map;

import com.georgejrdev.auxiliar.processing.interfaces.Help;


public class HelpImpl implements Help{

    final Map<String,String> COMMAND_SYNTAX = new HashMap<String,String>() {{
        put("-h","tkd -h");
        put("-t","tkd -t 'Text' pt");
        put("-c","tkd -c 'Text'");
        put("-td","tkd -td show|add|check|uncheck parameter");
        put("add","tkd -td add content");
        put("check","tkd -td check numberID");
        put("uncheck","tkd -td uncheck numberID");
        put("delete","tkd -td delete numberID");
    }};


    @Override
    public void showCommands(){
        System.out.println("tkd -h -> Show all commands");
        System.out.println("");
        System.out.println("tkd -t 'text' -> Translate a text");
        System.out.println("");
        System.out.println("tkd -c -> Chat with ChatGPT");
        System.out.println("");
        System.out.println("tkd -td -> ToDo List");
        System.out.println("tkd -td show-> Show all Tasks");
        System.out.println("tkd -td add 'content' -> New Task in ToDo List");
        System.out.println("tkd -td check id -> Mark the Task as completed");
        System.out.println("tkd -td unckeck id -> Unmark the Task as completed");
        System.out.println("tkd -td delete id -> Delete Task");
    }


    @Override
    public void showCorrectSintax(String command){
        System.out.println("Syntax Error: the correct syntax is -> " + getCommandSyntax(command));
        System.out.println("Use tkd -h to find out more");
    }

    
    @Override
    public String getCommandSyntax(String command){
        return COMMAND_SYNTAX.get(command);
    }
}