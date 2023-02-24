package com.dddeventsourcing.demo.orders.events;

public class ProductAddedEvent {

    private String productId;
    private String name;
    private int amount;
    private double pricePerUnit;

    public ProductAddedEvent(String productId, String name, int amount, double pricePerUnit) {
        this.productId = productId;
        this.name = name;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }
}
