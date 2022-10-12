package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.tripled.example.vocabulary.Amount.amount;
import static io.tripled.example.vocabulary.Amount.mandatoryAmount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.contains;

class AmountTest {

    @Test
    void success() {
        final FactoryResult<Amount> positiveNumber = Amount.amount(42);

        positiveNumber.onSuccess(AmountTest::isFortytwo);
        positiveNumber.onFailure(AmountTest::failTheTest);
    }

    @Test
    void zeroIsAllowed() {
        final FactoryResult<Amount> positiveNumber = Amount.amount(0);

        positiveNumber.onSuccess(AmountTest::isZero);
        positiveNumber.onFailure(AmountTest::failTheTest);
    }

    @Test
    void notNegative() {
        final FactoryResult<Amount> negativeNumber = Amount.amount(-666);

        negativeNumber.onFailure(AmountTest::hasValidationErrorsForNegativeValue);
        negativeNumber.onSuccess(AmountTest::failTheTest);
    }

    @Test
    void smallerThan1000() {
        final FactoryResult<Amount> negativeNumber = Amount.amount(1234);

        negativeNumber.onFailure(AmountTest::hasValidationErrorsForTooLarge);
        negativeNumber.onSuccess(AmountTest::failTheTest);
    }

    @Test
    void sumOfAmounts() {
        final FactoryResult<Amount> sum = mandatoryAmount(41).plus(Amount.ONE);

        sum.onSuccess(AmountTest::isFortytwo);
        sum.onFailure(AmountTest::failTheTest);
    }

    @Test
    void sumsAreValidated() {
        final FactoryResult<Amount> sum = mandatoryAmount(999).plus(mandatoryAmount(235));

        sum.onFailure(AmountTest::hasValidationErrorsForSumTooLarge);
        sum.onSuccess(AmountTest::failTheTest);
    }

    @Test
    void detractionsAreValidated() {
        final FactoryResult<Amount> sum = Amount.ONE.minus(mandatoryAmount(2));

        sum.onFailure(AmountTest::hasValidationErrorsForDetractionsTooSmall);
        sum.onSuccess(AmountTest::failTheTest);
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