package com.creditcard.luhn.impl;
import com.creditcard.luhn.CreditCardNumberValidator;
import org.junit.Before;
import org.junit.Test;

import static com.creditcard.luhn.Algorithm.luhn;
import static com.creditcard.luhn.UserInputValidator.defaultInputValidator;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test some of plumbing
 */
public class CreditCardNumberValidatorTest {

    private CreditCardNumberValidator creditCardNumberValidator;

    @Before
    public void before() {
        creditCardNumberValidator = CreditCardNumberValidator.create(defaultInputValidator(), luhn());
    }

    @Test
    public void validNumberWith() {
        assertTrue(creditCardNumberValidator.isValid("49927398716"));
    }

    @Test
    public void invalidNumberWith() {
        assertFalse(creditCardNumberValidator.isValid("49927398710"));
    }

    @Test(expected = CreditCardNumberValidationException.class)
    public void validNumberWithBadCharacter() {
        assertFalse(creditCardNumberValidator.isValid("4992739-8710"));
    }
}