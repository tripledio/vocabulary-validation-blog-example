package io.tripled.example.console;

import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.vocabulary.*;

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
        if(split.length < 3) return "Unable to parse command. Expected three arguments";

        // We map the data request, specific for this adapter, to our internal, well known domain primitives.
        final FactoryResult<Name> name = Name.name(split[0]);
        final FactoryResult<ShoeSize> shoeSize = ShoeSize.shoeSize(split[1]);
        final FactoryResult<Amount> amount = Amount.amount(split[2]);

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

