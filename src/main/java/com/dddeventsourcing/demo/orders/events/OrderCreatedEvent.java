package com.dddeventsourcing.demo.orders.events;


public record OrderCreatedEvent(String id, String status) {
}
