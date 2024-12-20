package com.georgejrdev.utils.exceptions;

public class AIKeyNotFound extends Exception {

    public AIKeyNotFound(){
        super();
    }
    
    public AIKeyNotFound(String message){
        super(message);
    }

    public AIKeyNotFound(String message, Throwable cause){
        super(message, cause);
    }

    public AIKeyNotFound(Throwable cause){
        super(cause);
    }

}
