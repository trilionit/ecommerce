package com.trilion.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trilion.ecommerce.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
  public List<Address> getByUserId(Long userId);

  public Address getByUserIdAndPrimaryAddress(Long userId, Boolean isPrimary);
}
