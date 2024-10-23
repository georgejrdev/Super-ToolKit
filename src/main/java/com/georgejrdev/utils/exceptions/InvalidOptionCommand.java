package com.georgejrdev.utils.exceptions;

public class InvalidOptionCommand extends Exception {
    
    public InvalidOptionCommand(){
        super();
    }
    
    public InvalidOptionCommand(String message){
        super(message);
    }

    public InvalidOptionCommand(String message, Throwable cause){
        super(message, cause);
    }

    public InvalidOptionCommand(Throwable cause){
        super(cause);
    }
}