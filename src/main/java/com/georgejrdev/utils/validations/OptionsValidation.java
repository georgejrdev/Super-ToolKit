package com.georgejrdev.utils.validations;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedHashMap;

import com.georgejrdev.utils.exceptions.AIKeyNotFound;
import com.georgejrdev.utils.exceptions.InvalidOptionCommand;
import com.georgejrdev.utils.exceptions.UnexpectedNumberOfParameters;

public class OptionsValidation {

    static private List<String> TODO_OPTIONS = new ArrayList<String>(){{
        add("add");
        add("remove");
        add("list");
        add("check");
        add("uncheck");
    }};

    static private List<String> PARSE_OPTIONS = new ArrayList<String>(){{
        add("watch");
    }};

    static private List<String> CONVERT_OPTIONS = new ArrayList<String>(){{
        add("image");
        add("video");
        add("audio");
    }};

    static private Map<String, List<String>> AVAILABLE_OPTIONS = new LinkedHashMap<String, List<String>>(){{
        put("todo", TODO_OPTIONS);
        put("parse", PARSE_OPTIONS);
        put("convert", CONVERT_OPTIONS);
    }};

    static public void optionIsAvailable(String command, String[] options) throws InvalidOptionCommand{
        
        command = command.toLowerCase();
        
        for (String option : options){
            option = option.toLowerCase();

            if (AVAILABLE_OPTIONS.get(command) == null) {
                throw new InvalidOptionCommand();
            }

            if (option == null){
                throw new InvalidOptionCommand();
            }

            if(!AVAILABLE_OPTIONS.get(command).contains(option)){
                throw new InvalidOptionCommand();
            }
        }
    }

    static public void expectedQuantityOfParameters(String args[], int[] quantity) throws UnexpectedNumberOfParameters{

        boolean valid = false;

        for (int i = 0; i < quantity.length; i++){
            if (args.length == quantity[i]){
                valid = true;
                break;
            }
        }

        if (!valid){
            throw new UnexpectedNumberOfParameters();
        }
    }

    static public void parameterIsAvailable(String[] parameters) throws IllegalArgumentException{

        for (String parameter : parameters){
            parameter = parameter.toLowerCase();

            if (parameter == null){
                throw new IllegalArgumentException();
            }
    
            if (parameter.isEmpty()){
                throw new IllegalArgumentException();
            }
        }
    }

    static public void isAIKeyConfigured() throws AIKeyNotFound {
        if (System.getenv("STK_GEMINI_API_KEY") == null){
            throw new AIKeyNotFound();
        }
    }
}