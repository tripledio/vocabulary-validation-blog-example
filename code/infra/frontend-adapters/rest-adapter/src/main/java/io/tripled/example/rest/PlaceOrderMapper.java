package io.tripled.example.rest;

import io.tripled.example.api.PlaceOrderCommand;
import io.tripled.example.vocabulary.*;

final class PlaceOrderMapper {
    private PlaceOrderMapper() {
    }

    static FactoryResult<PlaceOrderCommand> mapRequestToCommand(PlaceOrderRequest request) {
        final FactoryResult<Name> name = Name.name(request.getName());
        final FactoryResult<ShoeSize> shoeSize = ShoeSize.shoeSize(request.getSize());
        final FactoryResult<Amount> amount = Amount.amount(request.getAmount());

        final ValidationResult validationResult = name.validationResult().merge(shoeSize.validationResult()).merge(amount.validationResult());

        return FactoryResult.createIfValid(validationResult, () -> buildCommand(name, shoeSize, amount));
    }

    private static PlaceOrderCommand buildCommand(FactoryResult<Name> name,
                                                  FactoryResult<ShoeSize> shoeSize,
                                                  FactoryResult<Amount> amount) {
        return PlaceOrderCommand.newBuilder()
                .withAmount(amount.mandatoryValidInstance())
                .withName(name.mandatoryValidInstance())
                .withShoeSize(shoeSize.mandatoryValidInstance())
                .build();
    }
}