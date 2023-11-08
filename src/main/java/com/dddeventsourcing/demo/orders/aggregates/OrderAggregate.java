package com.dddeventsourcing.demo.orders.aggregates;

import com.dddeventsourcing.demo.orders.commands.AddProductCommand;
import com.dddeventsourcing.demo.orders.commands.CreateOrderCommand;
import com.dddeventsourcing.demo.orders.events.OrderCreatedEvent;
import com.dddeventsourcing.demo.orders.events.ProductAddedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

@Aggregate
public class OrderAggregate {
    private static final Logger log = LoggerFactory.getLogger(OrderAggregate.class);

    @AggregateIdentifier
    private String orderId;
    private OrderStatus status;

    private Set<Product> products = new HashSet<>();

    private Payment payment;

    private Supplier supplier;

    private Customer customer;

    public OrderAggregate() {
    }


    @CommandHandler
    public OrderAggregate(CreateOrderCommand command) {
        AggregateLifecycle.apply(new OrderCreatedEvent(command.id(), OrderStatus.DRAFT.toString()));
    }

    @EventSourcingHandler
    public void on(OrderCreatedEvent event) {
        this.orderId = event.id();
        this.status = OrderStatus.valueOf(event.status());
        log.info(toString());
    }

    @CommandHandler
    public void handle(AddProductCommand command) {
        if (status != OrderStatus.DRAFT) {
            throw new IllegalStateException("Order not in draft state. Changes to the order not allowed");
        }

        // other business rules

        var event = new ProductAddedEvent(
                command.getProductId(),
                command.getName(),
                command.getAmount(),
                command.getPricePerUnit()
        );
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(ProductAddedEvent event) {
        var product = new Product(event.getProductId(), event.getName(), event.getAmount(), event.getPricePerUnit());
        products.remove(product);
        products.add(product);

        log.info(toString());
    }

    @Override
    public String toString() {
        return "OrderAggregate{" +
                "orderId='" + orderId + '\'' +
                ", status=" + status +
                ", products=" + products +
                '}';
    }
}
