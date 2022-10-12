package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import static io.tripled.example.vocabulary.Amount.amount;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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


}