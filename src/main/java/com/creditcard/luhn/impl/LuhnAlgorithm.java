
package com.creditcard.luhn.impl;

import com.creditcard.luhn.Algorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.stream.IntStream;

import static java.lang.String.format;

/**
 * Credit card validator that uses the Luhn algorithm
 */
public  final class LuhnAlgorithm implements Algorithm {

    private static Logger log = LoggerFactory.getLogger(LuhnAlgorithm.class);

    private static final int MODULUS = 10;

    private final Products products = new Products();

    @Override
    public boolean isValid(int[] digits) {
        return modulus(digits) == 0;
    }

    private int modulus(int[] digits) {
        int total = 0;
        for (int i = 0; i < digits.length; i++) {
            int rightOffset = digits.length - 1 - i;
            total += value(digits[i], useWeight(rightOffset));
        }

        if (total == 0) {
            throw new CreditCardNumberValidationException(format("Invalid number %s, total was zero", Arrays.toString(digits)));
        }

        int modulus = total % MODULUS;
        if (log.isDebugEnabled()) {
            log.debug(format("Deriving modulus off %s, total: %d, modulus: %d", Arrays.toString(digits), total, modulus));
        }
        return modulus;
    }

    private boolean useWeight(int rightOffset) {
        return rightOffset % 2 != 0;
    }

    private int value(int digit, boolean useWeight) {
        return useWeight ? products.product(digit) : digit;
    }

    /*
     * Generate an array of "products" - this is actually the digit multiplied by 2,
     * with logic to reduce a double digit to single digit
     */
    private static class Products {

        private static final int[] PRODUCTS = IntStream
                .rangeClosed(0, 10)
                .map(Products::generateProduct)
                .toArray();

        private static int generateProduct(int digit) {
            int weight = digit * 2;
            if (weight > 9) {
                weight = weight - 9;
            }
            return weight;
        }

        int product(int digit) {
            return PRODUCTS[digit];
        }
    }
}

