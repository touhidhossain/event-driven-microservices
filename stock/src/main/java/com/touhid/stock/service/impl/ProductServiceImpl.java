package com.touhid.stock.service.impl;

import com.touhid.stock.domain.Product;
import com.touhid.stock.repository.ProductRepository;
import com.touhid.stock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Optional<Product> getProductById(long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public Optional<Product> getProductByIdWithWriteLock(long productId) {
        return productRepository.getProductsByProductIdWithWriteLock(productId);
    }
}
