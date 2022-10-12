package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.tripled.example.vocabulary.ShoeSize.SIZE_TWENTY_SEVEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.fail;

class ShoeSizeTest {

    @Test
    void happyPath() {

        final ShoeSize shoeSize = ShoeSize.shoeSize("27").mandatoryValidInstance();

        assertThat(shoeSize.name(), equalTo(SIZE_TWENTY_SEVEN.name()));
    }

    @Test
    void unknownSize() {
        final FactoryResult<ShoeSize> factoryResult = ShoeSize.shoeSize("333");
        factoryResult.onSuccess(ShoeSizeTest::failTheTest);
        factoryResult.onFailure(ShoeSizeTest::hasValidationErrorForUnknownShoeSize);
    }

    @Test
    void notANumber() {
        final FactoryResult<ShoeSize> factoryResult = ShoeSize.shoeSize("boom");
        factoryResult.onSuccess(ShoeSizeTest::failTheTest);
        factoryResult.onFailure(ShoeSizeTest::hasValidationErrorForNotANumber);
    }

    private static void hasValidationErrorForUnknownShoeSize(List<String> x) {
        assertThat(x, contains("Unknown shoe Size 333"));
    }

    private static void hasValidationErrorForNotANumber(List<String> x) {
        assertThat(x, contains("boom is not a valid Integer"));
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