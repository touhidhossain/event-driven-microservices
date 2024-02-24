package com.touhid.stock.kafka;


import com.touhid.basedomains.dto.OrderEvent;
import com.touhid.stock.domain.Product;
import com.touhid.stock.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderEventConsumerServiceImpl implements OrderEventConsumerService {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderEventConsumerServiceImpl.class);
    private final ProductService productService;
    @Override
    @Transactional
    public void consumeOrderEvent(OrderEvent orderEvent) {
        LOGGER.info("Got an Order Event: {}", orderEvent.getMessage());
        Optional<Product> optionalProduct = productService
                .getProductByIdWithWriteLock(orderEvent.getOrder().getProductId());
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            if(product.getInStock() >= orderEvent.getOrder().getQuantity()) {
                product.setInStock(product.getInStock() - orderEvent.getOrder().getQuantity());
            }
        }
    }
}
