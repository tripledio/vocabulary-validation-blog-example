package io.tripled.example.vocabulary;

import static io.tripled.example.vocabulary.FactoryResult.failure;
import static io.tripled.example.vocabulary.FactoryResult.success;

public enum ShoeSize {
    SIZE_TWENTY_SEVEN,
    SIZE_TWENTY_EIGHT,
    SIZE_THIRTY,
    SIZE_THIRTY_ONE_HALF,
    SIZE_THIRTY_TWO_HALF,
    SIZE_FIFTY,
    SIZE_FIFTY_TWO,
    SIZE_FIFTY_SIX;

    public static FactoryResult<ShoeSize> shoeSize(String value) {
        try {
            final int shoeNumber = Integer.parseInt(value);
            return mapShoeNumber(shoeNumber);
        } catch (NumberFormatException ex) {
            return FactoryResult.failure(value + " is not a valid Integer");
        }
    }

    private static FactoryResult<ShoeSize> mapShoeNumber(int shoeNumber) {
        return switch (shoeNumber) {
            case 27 -> success(SIZE_TWENTY_SEVEN);
            case 28 -> success(SIZE_TWENTY_EIGHT);
            case 315 -> success(SIZE_THIRTY_ONE_HALF);
            //...
            case 50 -> success(SIZE_FIFTY);
            default -> failure("Unknown shoe Size " + shoeNumber);
        };
    }
}
