package com.creditcard.luhn;

import com.creditcard.luhn.impl.LuhnAlgorithm;

/**
 * An algorithm to check if number (e.g. credit card) is valid or not
 */
public interface Algorithm {

    static Algorithm luhn() {
        return new LuhnAlgorithm();
    }

    /**
     * Validate number
     *
     * @param digits number to validate
     * @return true if valid, false otherwise
     */
    boolean isValid(int[] digits);
}