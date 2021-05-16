package com.creditcard.luhn.impl;


import com.creditcard.luhn.UserInputValidator;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;

public class UserInputValidatorImplTest {

    private static final int[] EXPECTED = new int[]{4, 9, 9, 2, 7, 3, 9, 8, 7, 1, 6};

    private UserInputValidator userInputValidator;

    @Before
    public void before() {
        userInputValidator = new UserInputValidatorImpl();
    }

    //
    // Valid
    //

    @Test
    public void validNumberWithNoSpaces() {
        doValidationPassTest("49927398716");
    }

    @Test
    public void validNumberWithInternalSpaces() {
        doValidationPassTest("499 273 987 16");
    }

    @Test
    public void validNumberWithLeadingSpace() {
        doValidationPassTest(" 49927398716");
    }

    @Test
    public void validNumberWithTrailingSpace() {
        doValidationPassTest("49927398716 ");
    }

    @Test
    public void validNumberWithLeadingTrailingAndInternalSpaces() {
        doValidationPassTest(" 499 273 987 16 ");
    }

    @Test
    public void validNumberWithDoubleSpaces() {
        doValidationPassTest("  499  273  987  16  ");
    }

    //
    // Test cases on invalid user input
    //

    @Test
    public void validNumberWithInvalidCharacterThrowsException() {
        doFailValidationTest("4992-7398716", "Invalid user input: non-valid digit: '-' detected in '4992-7398716', valid character: ' '");
    }

    //
    // Edge cases
    //

    @Test
    public void passInNullThrowsException() {
        doFailValidationTest(null, "Invalid user input: number was empty: 'null'");
    }

    @Test
    public void passInEmptyStringThrowsException() {
        doFailValidationTest("", "Invalid user input: number was empty: ''");
    }

    @Test
    public void passInSomeSpacesThrowsException() {
        doFailValidationTest("   ", "Invalid user input: number was empty: '   '");
    }

    @Test
    public void passInNonValidCharactersThrowsException() {
        doFailValidationTest("£$%%^@@+_!", "Invalid user input: non-valid digit: '£' detected in '£$%%^@@+_!', valid character: ' '");
    }


    private void doValidationPassTest(String number) {
        assertThat(userInputValidator.validate(number), is(EXPECTED));
    }

    private void doFailValidationTest(String number, String message) {
        try {
            userInputValidator.validate(number);
            fail("expected runtime exception");
        } catch (Exception e) {
            assertThat(e.getMessage(), is(message));
        }
    }
}