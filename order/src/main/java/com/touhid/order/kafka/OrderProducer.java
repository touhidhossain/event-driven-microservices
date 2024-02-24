package com.touhid.order.kafka;

import com.touhid.basedomains.dto.OrderEvent;
import com.touhid.order.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer implements OrderProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderProducer.class);
    private final NewTopic newTopic;
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;
    @Override
    public void sendMessage(OrderEvent orderEvent) {
        LOGGER.info("Order event: {}", orderEvent.toString());

        Message<OrderEvent> message = MessageBuilder
                .withPayload(orderEvent)
                .setHeader(KafkaHeaders.TOPIC, newTopic.name())
                .build();

        kafkaTemplate.send(message);
    }
}
