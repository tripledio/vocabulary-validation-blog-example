package io.tripled.example.vocabulary;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void processSuccess() {
        final FactoryResult<Integer> factoryResult = FactoryResult.success(42);

        final Boolean processed = factoryResult.process(FactoryResultTest::isFortyTwo, FactoryResultTest::failTest);

        assertTrue(processed);
    }

    @Test
    void processFailure() {
        final FactoryResult<Integer> factoryResult = FactoryResult.failure(List.of("Boom"));

        final Boolean processed = factoryResult.process(FactoryResultTest::failTest, FactoryResultTest::hasErrors);

        assertTrue(processed);
    }


    private static Boolean isFortyTwo(Integer x) {
        assertEquals(42, (int) x);
        return true;
    }

    private static Boolean failTest(Integer x) {
        fail("There should be no created instance but was [" + x + "]");
        return false;
    }

    private static Boolean hasErrors(ValidationResult v) {
        assertEquals(List.of("Boom"), v.messages);
        return true;
    }

    private static Boolean failTest(ValidationResult v) {
        fail("There should be no error messages but had [" + v.messages + "]");
        return false;
    }

}