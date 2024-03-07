package com.touhid.stock.controller;

import com.touhid.stock.domain.Product;
import com.touhid.stock.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class StockController {
    private final ProductService productService;
    @GetMapping("/stock/{productId}")
    public int getStock(@PathVariable("productId") long productId) {
        return productService.getProductById(productId).map(Product::getInStock)
                .orElseThrow(() -> new RuntimeException("Product not found for id: "+ productId));
    }
}
