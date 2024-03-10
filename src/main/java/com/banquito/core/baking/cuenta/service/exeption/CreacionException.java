package com.banquito.core.baking.cuenta.service.exeption;

public class CreacionException extends RuntimeException {

    public CreacionException(String message){
        super(message);
    }

    public CreacionException(String message, Exception cause){
        super(message, cause);
    }

}
