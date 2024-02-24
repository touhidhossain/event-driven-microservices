package com.touhid.order.service.impl;

import com.touhid.order.domain.ProductOrder;
import com.touhid.order.repository.ProductOrderRepository;
import com.touhid.order.service.ProductOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductOrderServiceImpl implements ProductOrderService {
    private final ProductOrderRepository productOrderRepository;
    @Override
    public ProductOrder save(ProductOrder productOrder) {
        return productOrderRepository.save(productOrder);
    }
}
