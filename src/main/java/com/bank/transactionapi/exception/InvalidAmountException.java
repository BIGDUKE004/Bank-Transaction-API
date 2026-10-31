package com.bank.transactionapi.exception;

public class InvalidAmountException extends RuntimeException {
    public InvalidAmountException(String s) {
        super(s);
    }
}
