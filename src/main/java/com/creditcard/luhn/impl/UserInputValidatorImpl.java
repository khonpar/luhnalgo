package com.creditcard.luhn.impl;

import com.creditcard.luhn.UserInputValidator;
import gnu.trove.TIntArrayList;

import static java.lang.Character.getNumericValue;
import static java.lang.Character.isDigit;
import static org.apache.commons.lang.StringUtils.isBlank;


/**
 * TODO  Add check -is string huge ? - avoid some sort of denial - to safeguard memory
 */
public final class UserInputValidatorImpl implements UserInputValidator {

    private static final char SPACE = ' '; // TODO : scope to extend this, configure new valid non digit characters

    @Override
    public int[] validate(String number) throws CreditCardNumberValidationException {
        if (isBlank(number)) {
            throw new CreditCardNumberValidationException("Invalid user input: number was empty: '" + number + "'");
        }

        number = number.trim();
        TIntArrayList integers = new TIntArrayList(number.length());
        for (int i = 0; i < number.length(); i++) {
            char character = number.charAt(i);
            if (isDigit(character)) {
                integers.add(getNumericValue(character));
            } else if (!isValidNonDigit(character)) {
                // we just fail fast here, dont bother going to end of string
                throw new CreditCardNumberValidationException(String.format("Invalid user input: non-valid digit: '%s' detected in '%s', valid character: '%s'", character, number, SPACE));
            }
        }

        return integers.toNativeArray();
    }

    private boolean isValidNonDigit(char character) {
        return character == SPACE;
    }
}