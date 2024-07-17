package com.epam.swissre.exception;

public class InvalidBeverageSizeException extends RuntimeException {

    public InvalidBeverageSizeException(String message, String invalidOrder) {
        super(String.format(message, invalidOrder));
    }
}
