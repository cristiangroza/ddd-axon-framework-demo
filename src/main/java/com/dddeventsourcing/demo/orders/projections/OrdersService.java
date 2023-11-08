package com.dddeventsourcing.demo.orders.projections;

import com.dddeventsourcing.demo.orders.events.OrderCreatedEvent;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrdersService {
    private static final Logger logger = LoggerFactory.getLogger(OrdersService.class);

    private final List<Order> orders = new ArrayList<>();

    @EventHandler
    public void handle(OrderCreatedEvent event) {
        logger.info("Handling event {}", event);
        var order = new Order(event.id(), event.status());
        orders.add(order);
    }

    public List<Order> getOrders() {
        return List.copyOf(orders);
    }
}
