package io.tripled.example.rest;
        
import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.vocabulary.*;

public class RestController {

    private final PlaceOrderAPI applicationApi;

    public RestController(PlaceOrderAPI applicationApi) {
        this.applicationApi = applicationApi;
    }

    //Annotated for whatever framework you prefer
    //@PostMapping("/PlaceOrder")
    String placeOrder(PlaceOrderRequest request) {

        // We map the data request, specific for this adapter, to our internal, well known domain primitives.
        final FactoryResult<Name> name = Name.name(request.getName());
        final FactoryResult<ShoeSize> shoeSize = ShoeSize.shoeSize(request.getSize());
        final FactoryResult<Amount> amount = Amount.amount(request.getAmount());

        final ValidationResult validationResult = name.validationResult().merge(shoeSize.validationResult()).merge(amount.validationResult());

        if (validationResult.isEmpty()) {
            //We invoke the api, who is blissfully unaware of any concrete adapter details
            applicationApi.placeOrder(name.mandatoryValidInstance(),
                    shoeSize.mandatoryValidInstance(),
                    amount.mandatoryValidInstance());
            return "Success";
        } else {
            return validationResult.toString();
        }
    }


}

