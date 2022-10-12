package io.tripled.example.domain;

import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.vocabulary.Amount;
import io.tripled.example.vocabulary.Name;
import io.tripled.example.vocabulary.ShoeSize;

public class PLaceOrderUseCase implements PlaceOrderAPI {
    @Override
    public void placeOrder(Name name, ShoeSize shoeSize, Amount amount) {
        //Implementation goes here
        //Something like this
        //final Order order = OrderFactory.create(name,shoeSize,amount);
        //orderRepository.add(order)
    }
}
