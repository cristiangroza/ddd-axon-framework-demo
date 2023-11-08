package com.dddeventsourcing.demo.orders.projections;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class OrdersProjectionResource {
    private final OrdersService ordersService;

    public OrdersProjectionResource(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/orders")
    public Flux<Order> getOrders() {
        return Flux.fromIterable(ordersService.getOrders());
    }
}
