package com.creditcard.luhn.impl;

import com.creditcard.luhn.Algorithm;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LuhnAlgorithmTest {

    private Algorithm algorithm;

    @Before
    public void before() {
        algorithm = new LuhnAlgorithm();
    }

    @Test
    public void isValidNumber() {
        assertTrue(algorithm.isValid(new int[]{4, 9, 9, 2, 7, 3, 9, 8, 7, 1, 6}));
    }

    @Test
    public void isValidNumberOneMoreExample() {
        assertTrue(algorithm.isValid(new int[]{7, 9, 9, 2, 7, 3, 9, 8, 7, 1, 3}));
    }

    @Test
    public void isInvalidNumber() {
        assertFalse(algorithm.isValid(new int[]{4, 9, 9, 2, 7, 3, 9, 8, 7, 1, 8}));
    }
}