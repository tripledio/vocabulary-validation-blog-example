package io.tripled.example.console;

import io.tripled.example.api.PlaceOrderCommand;
import io.tripled.example.vocabulary.*;

final class PlaceOrderParser {
    private PlaceOrderParser() {
    }

    static FactoryResult<PlaceOrderCommand> parsePlaceOrderCommand(String orderLine) {
        final String[] split = orderLine.split("\\s");
        if (split.length < 3) return FactoryResult.failure("Unable to parse command. Expected three arguments");

        return PlaceOrderCommand.newFactoryResultBuilder()
                .withAmount(Amount.amount(split[2]))
                .withName(Name.name(split[0]))
                .withShoeSize(ShoeSize.shoeSize(split[1]))
                .build();

    }


}