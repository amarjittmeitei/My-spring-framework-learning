package com.yawolart.minimalBanking.exceptions;

public class FindAccountException extends RuntimeException {
    public FindAccountException(String message) {
        super(message);
    }
}
