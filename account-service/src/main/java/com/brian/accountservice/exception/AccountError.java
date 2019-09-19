package com.brian.accountservice.exception;

public class AccountError extends RuntimeException{
    public AccountError(String message) {
        super(message);
    }
}
