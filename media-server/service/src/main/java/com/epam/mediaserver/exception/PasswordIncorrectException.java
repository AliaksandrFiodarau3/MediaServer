package com.epam.mediaserver.exception;

public class PasswordIncorrectException extends Exception {

    public PasswordIncorrectException(String message) {
        super(message);
    }

    public PasswordIncorrectException(String message, Exception e) {
        super(message, e);
    }
}
