package com.dddeventsourcing.demo.orders.resource;

import com.dddeventsourcing.demo.orders.commands.AddProductCommand;
import com.dddeventsourcing.demo.orders.commands.CreateOrderCommand;
import com.dddeventsourcing.demo.orders.resource.model.ProductApiModel;
import org.axonframework.extensions.reactor.commandhandling.gateway.ReactorCommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class OrderResource {
    private final ReactorCommandGateway commandGateway;

    public OrderResource(ReactorCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping("/orders/")
    public Mono<ResponseEntity<String>> createOrder() {
        var createOrderCommand = new CreateOrderCommand(UUID.randomUUID().toString());

        return commandGateway.send(createOrderCommand)
                .map(response -> response instanceof String orderId
                        ? ResponseEntity.status(HttpStatus.CREATED).body(orderId)
                        : ResponseEntity.internalServerError().build()
                );
    }

    @PostMapping("/orders/{orderId}/products")
    public Mono<ResponseEntity<Object>> addProductToOrder(@PathVariable String orderId,
                                                          @RequestBody Mono<ProductApiModel> productMono) {
        return productMono.map(product -> new AddProductCommand(
                orderId,
                product.productId(),
                product.name(),
                product.amount(),
                product.pricePerUnit())
        ).flatMap(commandGateway::send);
    }

}
