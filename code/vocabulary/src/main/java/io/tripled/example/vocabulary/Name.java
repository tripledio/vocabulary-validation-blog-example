package io.tripled.example.vocabulary;

import java.util.Objects;

import static io.tripled.example.vocabulary.FactoryResult.failure;
import static io.tripled.example.vocabulary.FactoryResult.success;


public class Name {
    public static final int MAX_LENGTH = 100;
    public static final int MIN_LENGTH = 2;
    public final String value;

    private Name(String value) {
        this.value = value;
    }

    //No exceptions were thrown during the making of this domain primitive
    public static FactoryResult<Name> name(String value) {
        if (value == null) return failure("Name value may not be NULL");
        if (value.isBlank()) return failure("Name value may not be blank");
        if (value.length() > MAX_LENGTH) return failure("Name length may not be larger than " + MAX_LENGTH + ". [" + value.substring(0, 20) + "]");
        if (value.length() < MIN_LENGTH) return failure("Name length may not be smaller than " + MIN_LENGTH + ". [" + value + "]");
        return success(new Name(value));
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
