package com.trilion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
  // Custom query Methods (if needed) can be defined here
}
