package com.turfsport.gameapp.domain.exceptions;

public class GameNotFoundException extends RuntimeException{

    public static final String NOT_FOUND = "No game found";

    public GameNotFoundException(String message) {
        super(message);
    }
}
