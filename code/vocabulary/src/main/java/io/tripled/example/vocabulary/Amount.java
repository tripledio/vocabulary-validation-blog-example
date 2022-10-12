package io.tripled.example.vocabulary;


import java.util.Objects;

public class Amount {
    private static final int MAX = 1000;
    private static final int MIN = 0;
    public static Amount ZERO = Amount.amount(MIN);
    public static Amount ONE = Amount.amount(1);

    public int value;

    private Amount(int value) {
        this.value = value;
    }

    public static Amount amount(int value) {
        if (isAmountTooSmall(value)) throw new RuntimeException("The amount [" + value + "] must be a larger than " + MIN);
        if (isAmountTooLarge(value)) throw new RuntimeException("The amount [" + value + "] must be smaller than " + MAX);
        return new Amount(value);
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

    public Amount plus(Amount x) {
        final int sum = this.value + x.value;
        if (isAmountTooLarge(sum)) throw new RuntimeException("[" + this.value + " plus " + x.value + "] must be smaller than " + MAX);
        else return Amount.amount(sum);
    }

    public Amount minus(Amount x) {
        final int value = this.value - x.value;
        if (isAmountTooSmall(value)) throw new RuntimeException("[" + this.value + " minus " + x.value + "] must be larger than " + MIN);
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

    private static boolean isAmountTooSmall(int value) {
        return value < MIN;
    }

    private static boolean isAmountTooLarge(int value) {
        return value >= MAX;
    }
}
