package com.dddeventsourcing.demo.orders.resource.model;

public record ProductApiModel(String productId,
                              String name,
                              int amount,
                              double pricePerUnit) {

}
