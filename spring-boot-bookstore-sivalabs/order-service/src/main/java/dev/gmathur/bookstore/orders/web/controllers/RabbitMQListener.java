package dev.gmathur.bookstore.orders.web.controllers;

import dev.gmathur.bookstore.orders.web.controllers.RabbitMQDemoController.MyPayload;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQListener {
    @RabbitListener(queues = "${orders.new-orders-queue}")
    public void handleNewOrder(MyPayload payload) {
        System.out.println("Received new order: " + payload.content());
    }

    @RabbitListener(queues = "${orders.delivered-orders-queue}")
    public void handleDeliveredOrder(MyPayload message) {
        System.out.println("Received delivered order: " + message.content());
    }
}
