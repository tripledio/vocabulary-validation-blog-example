package io.tripled.example.vocabulary;

import java.util.Objects;

public class FreeText {
    public static final int MAX_LENGTH = 50;
    public final String value;

    private FreeText(String value) {
        this.value = value;
    }

    public static FreeText freeText(String value) {
        if (value == null) throw new RuntimeException("Freetext value may not be NULL");
        if (value.isBlank()) throw new RuntimeException("Freetext value may not be blank");
        if (value.length() > MAX_LENGTH)
            throw new RuntimeException("Freetext length may not be larger than " + MAX_LENGTH + ". [" + value.substring(0, 20) + "]");
        return new FreeText(value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FreeText freeText = (FreeText) o;
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
