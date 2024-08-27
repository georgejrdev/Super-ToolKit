package com.georgejrdev.utils.exceptions;


public class UnexpectedNumberOfParameters extends Exception{

    public UnexpectedNumberOfParameters(){
        super();
    }

    
    public UnexpectedNumberOfParameters(String message){
        super(message);
    }


    public UnexpectedNumberOfParameters(String message, Throwable cause){
        super(message, cause);
    }


    public UnexpectedNumberOfParameters(Throwable cause){
        super(cause);
    }
}
