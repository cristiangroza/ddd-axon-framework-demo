package com.dddeventsourcing.demo.orders.resource;

import com.dddeventsourcing.demo.orders.commands.CreateOrderCommand;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class OrderResource {
    private final ReactorCommandGateway commandGateway;

    public OrderResource(ReactorCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/orders")
    public Mono<ResponseEntity<String>> createOrder() {
        var createOrderCommand = new CreateOrderCommand(UUID.randomUUID().toString());

        return commandGateway.send(createOrderCommand)
                .map(response -> response instanceof String orderId
                        ? ResponseEntity.status(HttpStatus.CREATED).body(orderId)
                        : ResponseEntity.internalServerError().build()
                );
    }


}
