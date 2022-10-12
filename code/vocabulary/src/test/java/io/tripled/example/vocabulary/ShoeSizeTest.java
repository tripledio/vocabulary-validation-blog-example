package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import static io.tripled.example.vocabulary.ShoeSize.SIZE_TWENTY_SEVEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.hamcrest.Matchers.contains;

class ShoeSizeTest {

    @Test
    void happyPath() {

        final ShoeSize shoeSize = ShoeSize.shoeSize("27");

        assertThat(shoeSize.name(), equalTo(SIZE_TWENTY_SEVEN.name()));
    }

    @Test
    void unknownSize() {
        assertThrows(RuntimeException.class, () -> ShoeSize.shoeSize("333"));
    }

    @Test
    void notANumber() {
        assertThrows(RuntimeException.class, () -> ShoeSize.shoeSize("boom"));
    }

    private static void failTheTest(ShoeSize shoeSize) {
        fail("There should be no created instance but was [" + shoeSize + "]");
    }

    private static void hasValidationErrorForUnknownShoeSize(ValidationResult x) {
        assertThat(x.messages, contains("Unknown shoe Size 333"));
    }

    private static void hasValidationErrorForNotANumber(ValidationResult x) {
        assertThat(x.messages, contains("boom is not a valid Integer"));
    }
}