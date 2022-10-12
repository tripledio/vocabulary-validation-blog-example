package io.tripled.example.console;

import io.tripled.example.api.PlaceOrderAPI;
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
        final String[] split = orderLine.split("\\s");

        // We map the data request, specific to this adapter, to our internal, well-known domain primitives.
        final Name name = name(split[0]);
        final Amount amount = amount(Integer.parseInt(split[1]));
        final ShoeSize size = shoeSize(split[1]);

        applicationApi.placeOrder(name, size, amount);

        // Lets tackle this next
        return "Success";
    }
}

