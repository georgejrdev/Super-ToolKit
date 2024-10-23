package com.georgejrdev.utils.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class Helper {
        
    static private List<String> HELP_COMMANDS = new ArrayList<String>(){{
        add("stk help : list all commands");    
        add("stk help [command] : list specific command");
    }};

    static private List<String> TRANSLATE_COMMANDS = new ArrayList<String>(){{
        add("stk translate [text] [language] : translate text");
    }};

    static private List<String> TODO_COMMANDS = new ArrayList<String>(){{
        add("stk todo add [text] : add ToDo");
        add("stk todo remove [id] : remove ToDo");
        add("stk todo list: list all ToDo's");
        add("stk todo check [id] : check ToDo");
        add("stk todo uncheck [id] : uncheck ToDo");
    }};

    static private List<String> PARSE_COMMANDS = new ArrayList<String>(){{
        add("stk parse [path/to/file] : parse Markdown File to HTML");
        add("stk parse watch [path/to/file] : parse Markdown File to HTML and watch changes in real time");
    }};

    static private List<String> CONVERT_COMMANDS = new ArrayList<String>(){{
        add("stk convert image [path/to/file] [*newType] : Convert image file to a new type");
        add("stk convert video [path/to/file] [*newType] : Convert video file to a new type");
        add("stk convert audio [path/to/file] [*newType] : Convert audio file to a new type");
    }};

    static private Map<String, List<String>> COMMANDS_SYNTAX = new LinkedHashMap<String,List<String>>(){{
        put("help", HELP_COMMANDS);
        put("translate", TRANSLATE_COMMANDS);
        put("todo", TODO_COMMANDS);
        put("parse", PARSE_COMMANDS);          
        put("convert", CONVERT_COMMANDS);
    }};

    static public void listCommands(){
        for(String command : COMMANDS_SYNTAX.keySet()){
            System.out.println("");
            
            for (String syntax : COMMANDS_SYNTAX.get(command)){
                System.out.println(syntax);
            }
        }
        System.out.println("");
    }

    static public void listCommands(String command){
        command = command.toLowerCase();
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
    
    static public void nonExistentCommand(String command){
        command = command.toLowerCase();

        System.out.println("");
        System.out.println("Command " + command + " does not exist");
        System.out.println("Use 'stk help' to list all available commands");
        System.out.println("");
    }

    static public void invalidOption(String command, String option){
        command = command.toLowerCase();
        option = option.toLowerCase();

        System.out.println("");
        System.out.println("Command " + command + " does not contain option " + option);
        System.out.println("Use 'stk help "+ command + "' to list all available options");
        System.out.println("");
    }
}