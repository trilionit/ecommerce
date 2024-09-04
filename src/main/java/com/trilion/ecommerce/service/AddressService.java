package com.trilion.ecommerce.service;

import java.util.Objects;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Address;
import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.repository.AddressRepository;
import com.trilion.ecommerce.repository.UserRepository;

@Service
public class AddressService {

  @Autowired
  AddressRepository addressRepository;

  @Autowired
  UserRepository userRepository;

  public Address addAddress(Address address, Long user_id) {
    User user = userRepository.findById(user_id).orElse(null);
    if (user != null) {
      address.setUser(user);
    }

    return addressRepository.save(address);
  }

  public List<Address> getAddresses() {
    return addressRepository.findAll();
  }

  public Address getAddressById(Long id) {
    Address address = addressRepository.findById(id).orElse(null);
    return address;
  }

  public List<Address> getUserAddress(Long user_id) {
    return addressRepository.getByUserId(user_id);
  }

  public Address updateUserAddress(Long id, Address addressInfo) {
    Address customerAddress = addressRepository.findById(id).get();

    if (Objects.nonNull(addressInfo.getPrimaryPhone()) && !"".equalsIgnoreCase(customerAddress.getPrimaryPhone())) {
      customerAddress.setPrimaryPhone(addressInfo.getPrimaryPhone());
    }
    if (Objects.nonNull(addressInfo.getSecondaryPhone()) && !"".equalsIgnoreCase(customerAddress.getSecondaryPhone())) {
      customerAddress.setSecondaryPhone(addressInfo.getSecondaryPhone());
    }

    if (Objects.nonNull(addressInfo.getAddress()) && !"".equalsIgnoreCase(customerAddress.getAddress())) {
      customerAddress.setAddress(addressInfo.getAddress());
    }

    if (Objects.nonNull(addressInfo.getCity()) && !"".equalsIgnoreCase(customerAddress.getCity())) {
      customerAddress.setCity(addressInfo.getCity());
    }

    if (Objects.nonNull(addressInfo.getState()) && !"".equalsIgnoreCase(customerAddress.getState())) {
      customerAddress.setState(addressInfo.getState());
    }

    if (Objects.nonNull(addressInfo.getZipcode()) && !"".equalsIgnoreCase(customerAddress.getZipcode())) {
      customerAddress.setZipcode(addressInfo.getZipcode());
    }
    customerAddress.setUpdatedAt();

    return addressRepository.save(customerAddress);
  }

  public void deleteAddress(Long id) {
    addressRepository.deleteById(id);
  }
}
