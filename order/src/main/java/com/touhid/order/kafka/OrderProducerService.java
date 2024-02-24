package com.touhid.order.kafka;

import com.touhid.basedomains.dto.OrderEvent;

public interface OrderProducerService {
    void sendMessage(OrderEvent orderEvent);
}
