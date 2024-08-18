package com.trilion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.Orders;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
  // Custom query Methods (if needed) can be defined here
  // List<Orders> findByProductId(Long productId);

  // List<Orders> findByUserId(Long userId);
}
