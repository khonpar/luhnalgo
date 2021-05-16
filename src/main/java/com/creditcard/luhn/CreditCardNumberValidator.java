package com.creditcard.luhn;

import com.creditcard.luhn.impl.CreditCardNumberValidationException;

/**
 * Validate credit card number using underlying strategy (e.g. Luhn algorithm)
 * <p>
 * Given number is a string and algorithm requires int[] conversion required with some level of validation
 */
public interface CreditCardNumberValidator {

    static CreditCardNumberValidator create(UserInputValidator userInputValidator, Algorithm algorithm) {
        return number -> {
            int[] digits = userInputValidator.validate(number);
            return algorithm.isValid(digits);
        };
    }

    /**
     * Apply validation
     *
     * @param number number to validate
     * @return true is number is valid, false otherwise
     * @throws CreditCardNumberValidationException if code cannot determine if valid (e.g. null or empty number passed in)
     */
    boolean isValid(String number) throws CreditCardNumberValidationException;
}