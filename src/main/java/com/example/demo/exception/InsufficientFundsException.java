package com.example.demo.exception;

public class InsufficientFundsException extends Exception{
    public InsufficientFundsException() {
        super("Insufficient funds for this transaction.");
    }

    public InsufficientFundsException(String message) {
        super(message);
    }
}
