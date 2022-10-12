package io.tripled.example.console;

import io.tripled.example.api.PlaceOrderCommand;
import io.tripled.example.vocabulary.*;

final class PlaceOrderParser {
    private PlaceOrderParser() {
    }

    static FactoryResult<PlaceOrderCommand> parsePlaceOrderCommand(String orderLine) {
        final String[] split = orderLine.split("\\s");
        if (split.length < 3) return FactoryResult.failure("Unable to parse command. Expected three arguments");

        final FactoryResult<Name> name = Name.name(split[0]);
        final FactoryResult<ShoeSize> shoeSize = ShoeSize.shoeSize(split[1]);
        final FactoryResult<Amount> amount = Amount.amount(split[2]);
        final ValidationResult validationResult = name.validationResult().merge(shoeSize.validationResult()).merge(amount.validationResult());
        return FactoryResult.createIfValid(validationResult, () -> buildCommand(name, shoeSize, amount));
    }

    private static PlaceOrderCommand buildCommand(FactoryResult<Name> name, FactoryResult<ShoeSize> shoeSize, FactoryResult<Amount> amount) {
        return PlaceOrderCommand.newBuilder()
                .withAmount(amount.mandatoryValidInstance())
                .withName(name.mandatoryValidInstance())
                .withShoeSize(shoeSize.mandatoryValidInstance())
                .build();
    }
}