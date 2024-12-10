package dev.gmathur.bookstore.orders.domain;

import dev.gmathur.bookstore.orders.domain.models.Address;
import dev.gmathur.bookstore.orders.domain.models.Customer;
import dev.gmathur.bookstore.orders.domain.models.OrderItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record CreateOrderRequest(
        @Valid @NotEmpty(message = "Items cannot be empty") Set<OrderItem> items,
        @Valid Customer customer,
        @Valid Address deliveryAddress) {}
