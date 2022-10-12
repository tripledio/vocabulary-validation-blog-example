package io.tripled.example.console;

import io.tripled.example.api.PlaceOrderAPI;

public class ConsoleAdapter {

    private final PlaceOrderAPI applicationApi;

    public ConsoleAdapter(PlaceOrderAPI applicationApi) {
        this.applicationApi = applicationApi;
    }

    String placeOrder(String orderLine) {
        // Do we validate before we invoke the application api?
        final String[] split = orderLine.split("\\s");
        final String name = split[0];
        final Integer amount = Integer.parseInt(split[1]);
        final String size = split[1];

        applicationApi.placeOrder(name, size, amount);

        // or is the implementation of PlaceOrderAPI responsible for this

        // And how do we capture and report potential validation errors?
        return "Success";
    }
}

