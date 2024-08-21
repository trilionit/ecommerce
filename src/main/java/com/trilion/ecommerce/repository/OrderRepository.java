package com.trilion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.Order;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
  // Custom query Methods (if needed) can be defined here
  List<Order> findByProductId(Long productId);

  List<Order> findByUserId(Long customerId);
}
