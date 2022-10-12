package io.tripled.example.rest;

import io.tripled.example.api.PlaceOrderCommand;
import io.tripled.example.vocabulary.*;

final class PlaceOrderMapper {
    private PlaceOrderMapper() {
    }

    static FactoryResult<PlaceOrderCommand> mapRequestToCommand(PlaceOrderRequest request) {
        return PlaceOrderCommand.newFactoryResultBuilder()
                .withShoeSize(ShoeSize.shoeSize(request.getSize()))
                .withName(Name.name(request.getName()))
                .withAmount(Amount.amount(request.getAmount()))
                .build();
    }


}