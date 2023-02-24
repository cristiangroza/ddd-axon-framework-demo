package com.dddeventsourcing.demo.orders.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class AddProductCommand {
    @TargetAggregateIdentifier
    private String orderId;

    private String productId;
    private String name;
    private int amount;
    private double pricePerUnit;

    public AddProductCommand(String orderId, String productId, String name, int amount, double pricePerUnit) {
        this.orderId = orderId;
        this.productId = productId;
        this.name = name;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }
}
