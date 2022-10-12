package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.fail;

class NameTest {
    @Test
    void success() {
        final FactoryResult<Name> positiveNumber = Name.name(buildText(5));

        positiveNumber.onSuccess(NameTest::isValidText);
        positiveNumber.onFailure(NameTest::failTheTest);
    }

    @Test
    void nullIsNotAllowed() {
        final FactoryResult<Name> negativeNumber = Name.name(null);

        negativeNumber.onFailure(NameTest::hasValidationErrorForNull);
        negativeNumber.onSuccess(NameTest::failTheTest);
    }

    @Test
    void textIsLimitedInSize() {
        final FactoryResult<Name> negativeNumber = Name.name(buildText(200));

        negativeNumber.onFailure(NameTest::hasValidationErrorForLength);
        negativeNumber.onSuccess(NameTest::failTheTest);
    }

    private static void hasValidationErrorForLength(ValidationResult x) {
        assertThat(x.messages, contains("Name length may not be larger than " + Name.MAX_LENGTH + ". [aaaaaaaaaaaaaaaaaaaa]"));
    }

    private static void failTheTest(ValidationResult validationResult) {
        fail("There should be no validation messages [" + validationResult.messages + "]");
    }

    private static void failTheTest(Name name) {
        fail("There should be no created instance but was [" + name + "]");
    }

    private static void isValidText(Name x) {
        assertEquals("aaaaa", x.value);
    }

    private static void hasValidationErrorForNull(ValidationResult x) {
        assertThat(x.messages, contains("Name value may not be NULL"));
    }

    private String buildText(int length) {
        return "a".repeat(Math.max(0, length));
    }
}