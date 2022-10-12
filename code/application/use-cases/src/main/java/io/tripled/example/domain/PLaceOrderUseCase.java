package io.tripled.example.domain;

import io.tripled.example.api.PlaceOrderAPI;
import io.tripled.example.api.PlaceOrderCommand;

public class PLaceOrderUseCase implements PlaceOrderAPI {
    @Override
    public void placeOrder(PlaceOrderCommand command) {
        //Implementation goes here
        //Something like this
        //final Order order = OrderFactory.create(command.name,command.shoeSize,command.amount);
        //orderRepository.add(order)
    }
}
