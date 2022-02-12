package com.sparta.ourshoppingmall.repository;

import com.sparta.ourshoppingmall.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product getByOrderId(Long orderId);
}
