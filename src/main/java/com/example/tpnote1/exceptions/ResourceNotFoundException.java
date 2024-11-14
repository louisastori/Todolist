package com.example.tpnote1.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String className) {
        super("Can't find the ressource of type "+className+" with identifier ");
    }

}