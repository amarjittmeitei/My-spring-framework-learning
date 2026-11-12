package com.yawolart.minimalBanking.exceptions;

public class InvalidAccountTypeException extends RuntimeException {
    public InvalidAccountTypeException(String message) {
        super(message);
    }
}
