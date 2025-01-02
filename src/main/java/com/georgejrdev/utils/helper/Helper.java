package com.georgejrdev.utils.helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import static com.georgejrdev.DefaultValues.*;

public class Helper {
        
    static private List<String> HELP_COMMANDS = new ArrayList<String>(){{
        add("stk"+ ANSI_YELLOW + " help" + ANSI_RESET + " : list all commands");    
        add("stk" + ANSI_YELLOW + " help" + ANSI_RESET + " [command] : list specific command");
    }};
    
    static private List<String> VERSION_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_YELLOW + " version" + ANSI_RESET + " : Get current version");
    }};

    static private List<String> UPDATE_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_YELLOW + " update" + ANSI_RESET + " : Get latest version");
    }};
    
    static private List<String> CONFIG_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_YELLOW + " config" + ANSI_RESET + " : Configure Gemini API Key");
    }};
    
    static private List<String> CHANGE_lOG_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_YELLOW + " changelog" + ANSI_RESET + " : View change log of the last version");
        add("stk" + ANSI_YELLOW + " changelog all" + ANSI_RESET + " : View change log of all versions");
    }};    

    static private List<String> TRANSLATE_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_PURPLE + " translate" + ANSI_RESET + " [text] [language] : translate text");
    }};
    
    static private List<String> TODO_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_RED + " todo add" + ANSI_RESET + " [text] : add ToDo");
        add("stk" + ANSI_RED + " todo remove" + ANSI_RESET + " [id] : remove ToDo");
        add("stk" + ANSI_RED + " todo list" + ANSI_RESET + " : show all ToDo's");
        add("stk" + ANSI_RED + " todo check" + ANSI_RESET + " [id] : check ToDo");
        add("stk" + ANSI_RED + " todo uncheck" + ANSI_RESET + " [id] : uncheck ToDo");
    }};
    
    static private List<String> PARSE_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_CYAN + " parse" + ANSI_RESET + " [path/to/file] : parse Markdown File to HTML");
        add("stk" + ANSI_CYAN + " parse watch" + ANSI_RESET + " [path/to/file] : parse Markdown File to HTML and watch changes in real time");
    }};
    
    static private List<String> CONVERT_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_BLUE + " convert image" + ANSI_RESET + " [path/to/file] [*newType] : Convert image file to a new type");
        add("stk" + ANSI_BLUE + " convert video" + ANSI_RESET + " [path/to/file] [*newType] : Convert video file to a new type");
        add("stk" + ANSI_BLUE + " convert audio" + ANSI_RESET + " [path/to/file] [*newType] : Convert audio file to a new type");
    }};
    
    static private List<String> COMMIT_MESSAGE_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_GREEN + " commit" + ANSI_RESET + " [description] : Get commit message");
    }};
    
    static private List<String> CHAT_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_BRIGHT_CYAN + " chat" + ANSI_RESET + " [message] : Send message to Gemini");
    }};
    
    static private List<String> QRCODE_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_BRIGHT_GREEN + " qrcode" + ANSI_RESET + " [url] [path to save] : Generate QRCode");
    }};
    
    static private List<String> RAM_VIEWER_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_BRIGHT_BLUE + " ramviewer" + ANSI_RESET + " : Start RAMViewer");
    }};

    static private List<String> KEY_COMMANDS = new ArrayList<String>(){{
        add("stk" + ANSI_RED + " key add" + ANSI_RESET + " [name] [value] : add key");
        add("stk" + ANSI_RED + " key remove" + ANSI_RESET + " [name] : remove key");
        add("stk" + ANSI_RED + " key list" + ANSI_RESET + " : show all keys");
        add("stk" + ANSI_RED + " key list" + ANSI_RESET + " [name] : show a specific key");
    }};
    
    static private Map<String, List<String>> COMMANDS_SYNTAX = new LinkedHashMap<String,List<String>>(){{
        put("help", HELP_COMMANDS);
        put("version", VERSION_COMMANDS);
        put("update", UPDATE_COMMANDS);
        put("config", CONFIG_COMMANDS);
        put("changelog", CHANGE_lOG_COMMANDS);
        put("translate", TRANSLATE_COMMANDS);
        put("todo", TODO_COMMANDS);
        put("parse", PARSE_COMMANDS);          
        put("convert", CONVERT_COMMANDS);
        put("commit", COMMIT_MESSAGE_COMMANDS);
        put("chat", CHAT_COMMANDS);
        put("qrcode", QRCODE_COMMANDS);
        put("ramviewer", RAM_VIEWER_COMMANDS);
        put("key", KEY_COMMANDS);
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