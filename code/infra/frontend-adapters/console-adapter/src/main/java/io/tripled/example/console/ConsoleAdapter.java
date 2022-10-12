package io.tripled.example.console;

import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.api.PlaceOrderCommand;
import io.tripled.example.vocabulary.*;

import static io.tripled.example.vocabulary.Amount.amount;

public class ConsoleAdapter {

    private final PlaceOrderAPI applicationApi;

    public ConsoleAdapter(PlaceOrderAPI applicationApi) {
        this.applicationApi = applicationApi;
    }

    String placeOrder(String orderLine) {
        final FactoryResult<PlaceOrderCommand> result = PlaceOrderParser.parsePlaceOrderCommand(orderLine);
        return result.process(this::placeOrder, this::validationErrorsToMessage);
    }

    private String validationErrorsToMessage(ValidationResult x) {
        return x.messages.toString();
    }

    private String placeOrder(PlaceOrderCommand command) {
        applicationApi.placeOrder(command);
        return "Success";
    }

}

