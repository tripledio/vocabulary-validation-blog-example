package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import static io.tripled.example.vocabulary.Amount.amount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.contains;

class AmountTest {

    @Test
    void happyPath() {
        final Amount positiveNumber = amount(42);

        assertThat(positiveNumber.value, equalTo(42));
    }

    @Test
    void equals() {
        final Amount positiveNumber = amount(42);

        assertEquals(positiveNumber, amount(42));
    }

    @Test
    void zeroIsAllowed() {
        final Amount positiveNumber = amount(0);

        assertThat(positiveNumber.value, equalTo(0));
    }

    @Test
    void notNegative() {
        assertThrows(RuntimeException.class, () -> amount(-666));
    }

    @Test
    void smallerThan1000() {
        assertThrows(RuntimeException.class, () -> amount(1244));
    }

    @Test
    void sumOfAmounts() {
        final Amount sum = amount(41).plus(Amount.ONE);

        assertThat(sum.value, equalTo(42));
    }

    @Test
    void sumsAreValidated() {
        assertThrows(RuntimeException.class, () -> amount(999).plus(amount(235)));
    }

    @Test
    void subtractionsAreValidated() {
        assertThrows(RuntimeException.class, () -> Amount.ONE.minus(amount(2)));
    }


    private static void failTheTest(ValidationResult validationResult) {
        fail("There should be no validation messages [" + validationResult.messages + "]");
    }

    private static void failTheTest(Amount positiveNumber) {
        fail("There should be no created instance but was [" + positiveNumber + "]");
    }

    private static void isFortytwo(Amount x) {
        assertEquals(42, x.value);
    }

    private static void isZero(Amount x) {
        assertEquals(0, x.value);
    }

    private static void hasValidationErrorsForNegativeValue(ValidationResult x) {
        assertThat(x.messages, contains("The amount [-666] must be a larger than 0"));
    }

    private static void hasValidationErrorsForTooLarge(ValidationResult x) {
        assertThat(x.messages, contains("The amount [1234] must be smaller than 1000"));
    }

    private static void hasValidationErrorsForSumTooLarge(ValidationResult x) {
        assertThat(x.messages, contains("[999 plus 235] must be smaller than 1000"));
    }

    private static void hasValidationErrorsForDetractionsTooSmall(ValidationResult x) {
        assertThat(x.messages, contains("[1 minus 2] must be larger than 0"));
    }

}