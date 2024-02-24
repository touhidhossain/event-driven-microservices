package com.touhid.stock.repository;

import com.touhid.stock.domain.Product;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query(value = "FROM Product p WHERE p.productId = ?1")
    Optional<Product> getProductsByProductIdWithReadLock(long productId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query(value = "FROM Product p WHERE p.productId = ?1")
    Optional<Product> getProductsByProductIdWithWriteLock(long productId);
}
