package com.trilion.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
  List<Cart> getByUserId(Long user_id);
}
