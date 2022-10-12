package io.tripled.example.vocabulary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationResult {
    public static final ValidationResult EMPTY = ValidationResult.builder().build();
    public final List<String> messages;

    private ValidationResult(Builder b) {
        messages = Collections.unmodifiableList(b.messages);

    }

    public static Builder builder() {
        return new Builder();
    }

    public static ValidationResult create(String singleMessage) {
        return ValidationResult.builder().addMessage(singleMessage).build();
    }

    public ValidationResult merge(ValidationResult other) {
        return ValidationResult.builder().addMessages(this.messages).addMessages(other.messages).build();
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }

    @Override
    public String toString() {
        return "ValidationResult{" + messages + '}';
    }

    public static class Builder {
        private final List<String> messages = new ArrayList<>();

        private Builder() {
        }

        public ValidationResult build() {
            return new ValidationResult(this);

        }

        public Builder addMessage(String message) {
            if (message != null && !message.isBlank()) messages.add(message);
            return this;
        }

        public Builder addMessages(List<String> messages) {
            messages.forEach(this::addMessage);
            return this;
        }

        public Builder add(ValidationResult validationResult) {
            return this.addMessages(validationResult.messages);
        }
    }

}
