package com.epam.swissre.exception;

public class EmptyOrderListException extends RuntimeException {

    public EmptyOrderListException(String message) {
        super(message);
    }
}
