package com.mar1a_ed.e_marketplace.exception;

public class NoRegisteredUsersException extends RuntimeException {

    public NoRegisteredUsersException(String message) {
        super(message);
    }
}
