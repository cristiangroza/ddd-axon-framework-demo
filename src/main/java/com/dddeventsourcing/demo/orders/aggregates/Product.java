package com.dddeventsourcing.demo.orders.aggregates;

import java.util.Objects;

class Product {
    private String id;
    private String name;
    private int amount;
    private double pricePerUnit;

    public Product(String id, String name, int amount, double pricePerUnit) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", pricePerUnit=" + pricePerUnit +
                '}';
    }
}
