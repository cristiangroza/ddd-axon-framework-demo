package com.dddeventsourcing.demo.orders.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class CreateOrderCommand {
    @TargetAggregateIdentifier
    private String id;

    public CreateOrderCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
