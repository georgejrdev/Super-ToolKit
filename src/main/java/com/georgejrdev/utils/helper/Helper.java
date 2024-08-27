package com.georgejrdev.utils.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;


public class Helper {
    
    private Map<String, List<String>> COMMANDS_SYNTAX;
    
    private List<String> HELP_COMMANDS;
    private List<String> TRANSLATE_COMMANDS;
    private List<String> TODO_COMMANDS;
    private List<String> PARSE_COMMANDS;

    public Helper(){
        
        this.HELP_COMMANDS = new ArrayList<String>(){{
            add("stk help : list all commands");
            add("stk -h : list all commands");
            
            add("stk help [command] : list specific command");
            add("stk -h [command] : list specific command");
        }};

        this.TRANSLATE_COMMANDS = new ArrayList<String>(){{
            add("stk translate [text] [language] : translate text");
            add("stk -t [text] [language] : translate text");
        }};

        this.TODO_COMMANDS = new ArrayList<String>(){{
            add("stk todo add [text] : add ToDo");
            add("stk -td add [text] : add ToDo");
            add("stk todo remove [id] : remove ToDo");
            add("stk -td remove [id] : remove ToDo");
            add("stk todo list: list all ToDo's");
            add("stk -td list : list all ToDo's");
            add("stk todo check [id] : check ToDo");
            add("stk -td check [id] : check ToDo");
            add("stk todo uncheck [id] : uncheck ToDo");
            add("stk -td uncheck [id] : uncheck ToDo");
        }};

        this.PARSE_COMMANDS = new ArrayList<String>(){{
            add("stk parse [path/to/file] : parse Markdown File to HTML");
            add("stk -p [path/to/file] : parse Markdown File to HTML");
            add("stk parse watch [path/to/file] : parse Markdown File to HTML and watch changes in real time");
            add("stk -p watch [path/to/file] : parse Markdown File to HTML and watch changes in real time");
        }};

        this.COMMANDS_SYNTAX = new LinkedHashMap<String,List<String>>(){{
            put("help", HELP_COMMANDS);
            put("translate", TRANSLATE_COMMANDS);
            put("todo", TODO_COMMANDS);
            put("parse", PARSE_COMMANDS);            
        }};
    }

    
    public void listCommands(){
        for(String command : COMMANDS_SYNTAX.keySet()){
            System.out.println("");
            for (String syntax : COMMANDS_SYNTAX.get(command)){
                System.out.println(syntax);
            }
        }
        System.out.println("");
    }
    

    public void listCommands(String command){

        command = command.toLowerCase();

        command = (command.equals("-h")) ? "help" : command;
        command = (command.equals("-t")) ? "translate" : command;
        command = (command.equals("-td")) ? "todo" : command;
        command = (command.equals("-p")) ? "parse" : command;

        List<String> syntaxes = COMMANDS_SYNTAX.get(command);

        if (syntaxes == null){
            invalidOption("help", command);
            return;
        }

        System.out.println("");
        for (String syntax : syntaxes){
            System.out.println(syntax);
        }
        System.out.println("");
    }
    
    
    public void nonExistentCommand(String command){

        command = command.toLowerCase();

        System.out.println("");
        System.out.println("Command " + command + " does not exist");
        System.out.println("Use 'stk help' to list all available commands");
        System.out.println("");
    }


    public void invalidOption(String command, String option){

        command = command.toLowerCase();
        option = option.toLowerCase();

        System.out.println("");
        System.out.println("Command " + command + " does not contain option " + option);
        System.out.println("Use 'stk help "+ command + "' to list all available options");
        System.out.println("");
    }
}