package com.example.learningjava;

public class Exceptionpack extends Exception{
    public Exceptionpack(String message){
        super(message);
    }

    public Exceptionpack(String message, Throwable cause){
        super(message, cause);
    }
}
