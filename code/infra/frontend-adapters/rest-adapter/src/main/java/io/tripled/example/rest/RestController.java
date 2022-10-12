package io.tripled.example.rest;

import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.api.PlaceOrderCommand;
import io.tripled.example.vocabulary.*;

public class RestController {

    private final PlaceOrderAPI applicationApi;

    public RestController(PlaceOrderAPI applicationApi) {
        this.applicationApi = applicationApi;
    }

    //Annotated for whatever framework you prefer
    //@PostMapping("/PlaceOrder")
    String placeOrder(PlaceOrderRequest request) {
        final FactoryResult<PlaceOrderCommand> result = PlaceOrderMapper.mapRequestToCommand(request);
        return result.process(this::placeOrder, this::validationErrorsToMessage);
    }


    private String placeOrder(PlaceOrderCommand command) {
        applicationApi.placeOrder(command);
        return "Success";
    }

    private String validationErrorsToMessage(ValidationResult x) {
        return x.messages.toString();
    }

}

