package com.dddeventsourcing.demo.orders.commands;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateOrderCommand(@TargetAggregateIdentifier String id) {
    @JsonCreator
    public CreateOrderCommand {
    }

}
