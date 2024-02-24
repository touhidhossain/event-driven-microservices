package com.touhid.stock.service;

import com.touhid.stock.domain.Product;

import java.util.Optional;

public interface ProductService {
    Product save(Product product);

    Optional<Product> getProductById(long productId);

    Optional<Product> getProductByIdWithWriteLock(long productId);
}
