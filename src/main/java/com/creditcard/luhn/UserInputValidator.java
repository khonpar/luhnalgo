package com.creditcard.luhn;

import com.creditcard.luhn.impl.CreditCardNumberValidationException;
import com.creditcard.luhn.impl.UserInputValidatorImpl;

/**
 * Validate user input - check string contains digits (and possibly some valid non numeric), converting it to an int[] array
 *
 * If its not possible to generate int[] array, throw CreditCardNumberValidationException with clear reason why not
 */
public interface UserInputValidator {

    public static UserInputValidator defaultInputValidator() {
        return new UserInputValidatorImpl();
    }

    /**
     * Validate user input, converting the string to an int array in the process.
     *
     * @param number number to validate
     * @return a primitive int array of numbers that have been validated
     * @throws CreditCardNumberValidationException if user input is not valid
     */
    int[] validate(String number) throws CreditCardNumberValidationException;
}