package io.tripled.example.rest;


import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.vocabulary.Amount;
import io.tripled.example.vocabulary.Name;
import io.tripled.example.vocabulary.ShoeSize;

import static io.tripled.example.vocabulary.Amount.amount;
import static io.tripled.example.vocabulary.Name.name;
import static io.tripled.example.vocabulary.ShoeSize.shoeSize;

public class RestController {

    private final PlaceOrderAPI applicationApi;

    public RestController(PlaceOrderAPI applicationApi) {
        this.applicationApi = applicationApi;
    }

    //Annotated for whatever framework you prefer
    //@PostMapping("/PlaceOrder")
    String placeOrder(PlaceOrderRequest request) {

        // We map the data request, specific to this adapter, to our internal, well-known domain primitives.
        final Name name = name(request.getName());
        final ShoeSize shoeSize = shoeSize(request.getSize());
        final Amount amount = amount(request.getAmount());

        //We invoke the API, which is blissfully unaware of any concrete adapter details
        applicationApi.placeOrder(name,shoeSize,amount);

        // Let's answer this questions next..
        return "messages";
    }
}

