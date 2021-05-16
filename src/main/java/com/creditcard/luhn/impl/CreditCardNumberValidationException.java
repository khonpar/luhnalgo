package com.creditcard.luhn.impl;

public class CreditCardNumberValidationException extends RuntimeException {

    private static final long serialVersionUID = 8992805674819578785L;

   public CreditCardNumberValidationException(String message) {
        super(message);
    }
}