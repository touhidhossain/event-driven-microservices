package com.touhid.email.kafka;

import com.touhid.basedomains.dto.OrderEvent;
import org.springframework.kafka.annotation.KafkaListener;

public interface OrderEventConsumerService {
    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    void consumeOrderEvent(OrderEvent orderEvent);
}
