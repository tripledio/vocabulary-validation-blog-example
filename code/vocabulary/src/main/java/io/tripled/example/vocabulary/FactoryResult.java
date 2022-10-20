package io.tripled.example.vocabulary;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class FactoryResult<T> {

    private final T createdInstance;
    private final ValidationResult validationResult;

    private FactoryResult(T createdInstance, ValidationResult validationResult) {
        if (createdInstance != null && validationResult != null) throw new RuntimeException("A factoryResult may not have a createdInstance and errorMessages");
        if (createdInstance == null && ((validationResult == null) || validationResult.isEmpty()))
            throw new RuntimeException("A factoryResult must have a createdInstance or errorMessages");
        this.createdInstance = createdInstance;
        this.validationResult = (validationResult == null) ? ValidationResult.EMPTY : validationResult;
    }

    public static <T> FactoryResult<T> success(T t) {
        return new FactoryResult<T>(t, null);
    }

    public static <T> FactoryResult<T> failure(List<String> errorMessages) {
        return new FactoryResult<>(null, ValidationResult.builder().addMessages(errorMessages).build());
    }

    public static <T> FactoryResult<T> failure(String... errorMessages) {
        return new FactoryResult<>(null, ValidationResult.builder().addMessages(Arrays.stream(errorMessages).toList()).build());
    }

    public static <T> FactoryResult<T> createIfValid(ValidationResult validationResult, Supplier<T> factoryMethod) {
        if (validationResult.isEmpty()) return FactoryResult.success(factoryMethod.get());
        else return FactoryResult.failure(validationResult);
    }

    public static <T> FactoryResult<T> failure(ValidationResult validationResult) {
        return failure(validationResult.messages);
    }

    public void onSuccess(Consumer<T> happyPathHandler) {
        if (hasValidInstance()) {
            happyPathHandler.accept(createdInstance);
        }
    }

    public void onFailure(Consumer<ValidationResult> errorHandler) {
        if (hasNoValidInstance()) {
            errorHandler.accept(validationResult);
        }
    }

    public <R> R process(Function<T, R> happyPathFunction, Function<ValidationResult, R> errorHandler) {
        if (hasValidInstance()) {
            return happyPathFunction.apply(createdInstance);
        } else {
            return errorHandler.apply(validationResult);
        }
    }

    public T mandatoryValidInstance() {
        if (hasNoValidInstance()) throw new RuntimeException("A valid instance was expected but there were unexpected errors " + validationResult.toString());
        else return createdInstance;
    }

    public ValidationResult validationResult() {
        return validationResult;
    }

    private boolean hasValidInstance() {
        return createdInstance != null;
    }

    private boolean hasNoValidInstance() {
        return createdInstance == null;
    }
}
