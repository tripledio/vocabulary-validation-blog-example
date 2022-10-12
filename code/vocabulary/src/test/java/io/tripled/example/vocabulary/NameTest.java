package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    void success() {
        final Name name = Name.name(buildText(5));

        assertEquals("aaaaa", name.value);
    }

    @Test
    void equals() {
        final Name name = Name.name(buildText(5));

        assertEquals(name, Name.name(buildText(5)));
    }

    @Test
    void nullIsNotAllowed() {
        assertThrows(RuntimeException.class, () -> Name.name(null));
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