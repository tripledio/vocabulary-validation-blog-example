package io.tripled.example.api;

import io.tripled.example.vocabulary.Amount;
import io.tripled.example.vocabulary.Name;
import io.tripled.example.vocabulary.ShoeSize;

public interface PlaceOrderAPI {
    void placeOrder(Name name, ShoeSize shoeSize, Amount amount);

}

