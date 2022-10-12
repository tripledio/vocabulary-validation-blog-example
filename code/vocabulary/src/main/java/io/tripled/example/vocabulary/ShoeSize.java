package io.tripled.example.vocabulary;

public enum ShoeSize {
    SIZE_TWENTY_SEVEN,
    SIZE_TWENTY_EIGHT,
    SIZE_THIRTY,
    SIZE_THIRTY_ONE_HALF,
    SIZE_THIRTY_TWO_HALF,
    SIZE_FIFTY,
    SIZE_FIFTY_TWO,
    SIZE_FIFTY_SIX;

    public static ShoeSize shoeSize(String value) {
        switch (Integer.parseInt(value)) {
            case 27:
                return SIZE_TWENTY_SEVEN;
            case 28:
                return SIZE_TWENTY_EIGHT;
            case 315:
                return SIZE_THIRTY_ONE_HALF;
            //...
            case 50:
                return SIZE_FIFTY;
            default:
                throw new RuntimeException("Unknown shoe Size " + value);
        }

    }
}
