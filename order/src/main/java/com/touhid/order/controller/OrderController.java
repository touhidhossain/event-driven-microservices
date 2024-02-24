package com.touhid.order.controller;

import com.touhid.basedomains.dto.Order;
import com.touhid.basedomains.dto.OrderEvent;
import com.touhid.order.domain.ProductOrder;
import com.touhid.order.kafka.OrderProducerService;
import com.touhid.order.repository.ProductOrderRepository;
import com.touhid.order.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    private final OrderProducerService orderProducerService;
    private final ProductOrderService productOrderService;

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
}
