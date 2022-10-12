package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import static io.tripled.example.vocabulary.ShoeSize.SIZE_TWENTY_SEVEN;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

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
}