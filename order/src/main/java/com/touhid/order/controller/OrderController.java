package com.touhid.order.controller;

import com.touhid.basedomains.dto.Order;
import com.touhid.basedomains.dto.OrderEvent;
import com.touhid.order.domain.ProductOrder;
import com.touhid.order.kafka.OrderProducerService;
import com.touhid.order.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderProducerService orderProducerService;
    private final ProductOrderService productOrderService;
    private final WebClient stockServiceClient;

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order) {
        order.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = OrderEvent
                .builder()
                .status("PENDING")
                .message("This order is on pending status!")
                .order(order).build();

        ProductOrder productOrder = ProductOrder
                .builder()
                .orderId(order.getOrderId())
                .productId(order.getProductId())
                .name(order.getName())
                .price(order.getPrice())
                .quantity(order.getQuantity())
                .build();

        productOrderService.save(productOrder);

        orderProducerService.sendMessage(orderEvent);

        return "Order placed successfully!";
    }

    @GetMapping("/order/{productId}")
    public Integer getStock(@PathVariable("productId") long productId) {
        return stockServiceClient.get().uri("/api/v1/stock/"+productId)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(Integer.class);
                    } else if (clientResponse.statusCode().is4xxClientError()) {
                        return clientResponse.createException()
                                .flatMap(Mono::error);
                    } else {
                        return clientResponse.createException()
                                .flatMap(Mono::error);
                    }
                }).block();
    }
}
