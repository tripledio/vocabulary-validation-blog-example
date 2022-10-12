package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void textIsLimitedInSize() {
        assertThrows(RuntimeException.class, () -> Name.name(buildText(200)));
    }

    private String buildText(int length) {
        return "a".repeat(Math.max(0, length));
    }
}