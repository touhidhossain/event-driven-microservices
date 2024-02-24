package com.touhid.order.repository;

import com.touhid.order.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderRepository extends JpaRepository<ProductOrder, String> {
}
