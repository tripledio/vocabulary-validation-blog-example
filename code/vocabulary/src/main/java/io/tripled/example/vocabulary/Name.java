package io.tripled.example.vocabulary;

import java.util.Objects;


public class Name {
    public static final int MAX_LENGTH = 100;
    public static final int MIN_LENGTH = 2;
    public final String value;

    private Name(String value) {
        this.value = value;
    }


    public static Name name(String value) {
        if (value == null) throw new RuntimeException("Name value may not be NULL");
        if (value.isBlank()) throw new RuntimeException("Name value may not be blank");
        if (value.length() > MAX_LENGTH) throw new RuntimeException("Name length may not be larger than " + MAX_LENGTH + ". [" + value.substring(0, 20) +
                                                                    "]");
        if (value.length() < MIN_LENGTH) throw new RuntimeException("Name length may not be smaller than " + MIN_LENGTH + ". [" + value + "]");
        return new Name(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name freeText = (Name) o;
        return value.equals(freeText.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
