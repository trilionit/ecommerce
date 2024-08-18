package com.trilion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
  // Custom query Methods (if needed) can be defined here
}
