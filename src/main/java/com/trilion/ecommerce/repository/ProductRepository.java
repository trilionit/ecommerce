package com.trilion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
  // Custom query Methods (if needed) can be defined here
}
