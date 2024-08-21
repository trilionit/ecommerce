package com.trilion.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
  // Custom query Methods (if needed) can be defined here
  User findByEmail(String email);
}
