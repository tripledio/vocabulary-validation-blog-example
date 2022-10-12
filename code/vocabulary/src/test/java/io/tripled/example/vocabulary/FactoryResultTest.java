package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class FactoryResultTest {

    @Test
    void success() {
        final FactoryResult<Integer> factoryResult = FactoryResult.success(42);

        factoryResult.onSuccess(FactoryResultTest::isFortyTwo);
        factoryResult.onFailure(FactoryResultTest::failTest);
    }

    @Test
    void failure() {
        final FactoryResult<Integer> factoryResult = FactoryResult.failure(List.of("Boom"));

        factoryResult.onFailure(FactoryResultTest::hasErrors);
        factoryResult.onSuccess(FactoryResultTest::failTest);
    }

    private static void isFortyTwo(Integer x) {
        assertEquals(42, (int) x);
    }

    private static void failTest(Integer x) {
        fail("There should be no created instance but was [" + x + "]");
    }

    private static void hasErrors(List<String> messages) {
        assertEquals(List.of("Boom"), messages);
    }

    private static void failTest(List<String> messages) {
        fail("There should be no error messages but had [" + messages + "]");
    }
}