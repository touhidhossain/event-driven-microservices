package com.touhid.email.kafka;


import com.touhid.basedomains.dto.OrderEvent;
import com.touhid.email.domain.Email;
import com.touhid.email.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderEventConsumerServiceImpl implements OrderEventConsumerService {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderEventConsumerServiceImpl.class);
    private final EmailService emailService;
    @Override
    public void consumeOrderEvent(OrderEvent orderEvent) {
        LOGGER.info("Got an Order Event: {}", orderEvent.getMessage());

        Email email = Email.builder()
                .message(orderEvent.getMessage())
                .orderId(orderEvent.getOrder().getOrderId())
                .addedOn(LocalDateTime.now()).build();

        emailService.save(email);
    }
}
