package com.epam.mediaserver.exception;

public class ValidateException extends Exception {

    public ValidateException(String message) {
        super(message);
    }

    public ValidateException(String message, Exception e) {
        super(message, e);
    }
}
