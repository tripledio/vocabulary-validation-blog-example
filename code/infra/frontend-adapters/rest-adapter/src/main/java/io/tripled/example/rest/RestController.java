package io.tripled.example.rest;


import io.tripled.example.api.PlaceOrderAPI;

public class RestController {

    private final PlaceOrderAPI applicationApi;

    public RestController(PlaceOrderAPI applicationApi) {
        this.applicationApi = applicationApi;
    }

    //Annotated for whatever framwwork you prefer
    //@PostMapping("/PlaceOrder")
    String placeOrder(PlaceOrderRequest request) {

        // Do we validate before we invoke the application api?
        applicationApi.placeOrder(request.getName(), request.getSize(), request.getAmount());

        // or is the implementation of PlaceOrderAPI resposible for this

        // And how do we capture and report potential validation errors?
        return "Success";
    }
}

