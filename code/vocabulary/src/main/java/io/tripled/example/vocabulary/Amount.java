package io.tripled.example.vocabulary;


import java.util.Objects;

import static io.tripled.example.vocabulary.FactoryResult.createIfValid;
import static io.tripled.example.vocabulary.FactoryResult.failure;
import static io.tripled.example.vocabulary.ValidationResult.create;

public class Amount {
    private static final int MAX = 1000;
    private static final int MIN = 0;
    public static Amount ZERO = Amount.mandatoryAmount(MIN);
    public static Amount ONE = Amount.mandatoryAmount(1);

    public int value;

    private Amount(int value) {
        this.value = value;
    }

    public static FactoryResult<Amount> amount(int amount) {
        final ValidationResult validationResult = validate(amount);
        return createIfValid(validationResult, () -> new Amount(amount));
    }

    public static FactoryResult<Amount> amount(String value) {
        try {
            final int amountNumber = Integer.parseInt(value);
            return amount(amountNumber);
        } catch (NumberFormatException ex) {
            return failure(value + " is not a valid Integer");
        }
    }

    public static Amount mandatoryAmount(int i) {
        return amount(i).mandatoryValidInstance();
    }


    @Override
    public String toString() {
        return "" + value;
    }

    public boolean largerThan(int x) {
        return value > x;
    }

    public boolean largerThan(Amount x) {
        return value > x.value;
    }

    public boolean isSmallerThan(Amount x) {
        return value < x.value;
    }

    public FactoryResult<Amount> plus(Amount x) {
        final int sum = this.value + x.value;
        if (isAmountTooLarge(sum)) return failure("[" + this.value + " plus " + x.value + "] must be smaller than " + MAX);
        else return Amount.amount(sum);
    }

    public FactoryResult<Amount> minus(Amount x) {
        final int value = this.value - x.value;
        if (isAmountTooSmall(value)) return failure("[" + this.value + " minus " + x.value + "] must be larger than " + MIN);
        else return Amount.amount(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Amount that = (Amount) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    private static ValidationResult validate(int value) {
        if (isAmountTooSmall(value)) return create("The amount [" + value + "] must be a larger than " + MIN);
        if (isAmountTooLarge(value)) return create("The amount [" + value + "] must be smaller than " + MAX);
        else return ValidationResult.EMPTY;
    }

    private static boolean isAmountTooSmall(int value) {
        return value < MIN;
    }

    private static boolean isAmountTooLarge(int value) {
        return value >= MAX;
    }
}
