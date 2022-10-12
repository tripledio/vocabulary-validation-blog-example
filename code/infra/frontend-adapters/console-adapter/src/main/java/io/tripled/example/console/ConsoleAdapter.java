package io.tripled.example.console;

import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.api.PlaceOrderCommand;
import io.tripled.example.vocabulary.Amount;
import io.tripled.example.vocabulary.Name;
import io.tripled.example.vocabulary.ShoeSize;

import static io.tripled.example.vocabulary.Amount.amount;
import static io.tripled.example.vocabulary.Name.name;
import static io.tripled.example.vocabulary.ShoeSize.shoeSize;

public class ConsoleAdapter {

    private final PlaceOrderAPI applicationApi;

    public ConsoleAdapter(PlaceOrderAPI applicationApi) {
        this.applicationApi = applicationApi;
    }

    String placeOrder(String orderLine) {
        // We map the data request, specific to this adapter
        final PlaceOrderCommand placeOrderCommand = buildPlaceOrderCommand(orderLine);

        //We invoke the API, which is blissfully unaware of any concrete adapter details
        applicationApi.placeOrder(placeOrderCommand);

        // Let's tackle this next
        return "Success";
    }

    private PlaceOrderCommand buildPlaceOrderCommand(String orderLine) {
        final String[] split = orderLine.split("\\s");
        final Name name = name(split[0]);
        final Amount amount = amount(Integer.parseInt(split[1]));
        final ShoeSize shoeSize = shoeSize(split[1]);
        return PlaceOrderCommand.newBuilder()
                .withAmount(amount)
                .withName(name)
                .withShoeSize(shoeSize)
                .build();
    }
}

