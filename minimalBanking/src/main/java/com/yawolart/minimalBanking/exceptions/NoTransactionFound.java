package com.yawolart.minimalBanking.exceptions;

public class NoTransactionFound extends RuntimeException {
    public NoTransactionFound(String message) {
        super(message);
    }
}
