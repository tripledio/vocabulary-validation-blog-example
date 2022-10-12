package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

class ValidationResultTest {
    @Test
    void happyPath() {
        final ValidationResult validationResult = ValidationResult.builder()
                .addMessage("Hi")
                .addMessages(List.of("one", "two"))
                .build();

        assertThat(validationResult.messages, contains("Hi", "one", "two"));
    }

    @Test
    void noEmptyMessages() {
        final ValidationResult validationResult = ValidationResult.builder()
                .addMessage("only message")
                .addMessage("")
                .addMessage("  ")
                .addMessage(null)
                .build();

        assertThat(validationResult.messages, contains("only message"));
    }
}