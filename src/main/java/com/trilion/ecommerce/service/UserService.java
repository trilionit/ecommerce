package com.trilion.ecommerce.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.User;

import com.trilion.ecommerce.repository.UserRepository;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public User save(User user) {
    return userRepository.save(user);
  }

  public User getUserById(Long id) {
    return userRepository.findById(id).orElse(null);
  }

  public User getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public List<User> getAllCustomers() {
    return userRepository.findAll();
  }

  public User updateUser(Long id, User userInfo) {
    User customer = this.getUserById(id);

    if (Objects.nonNull(userInfo.getFirstName()) && !"".equalsIgnoreCase(customer.getFirstName())) {
      customer.setFirstName(userInfo.getFirstName());
    }
    if (Objects.nonNull(userInfo.getLastName()) && !"".equalsIgnoreCase(customer.getLastName())) {
      customer.setLastName(userInfo.getLastName());
    }
    if (Objects.nonNull(userInfo.getEmail()) && !"".equalsIgnoreCase(customer.getEmail())) {
      customer.setEmail(userInfo.getEmail());
    }
    if (Objects.nonNull(userInfo.getAddress()) && !"".equalsIgnoreCase(customer.getAddress())) {
      customer.setAddress(userInfo.getAddress());
    }
    if (Objects.nonNull(userInfo.getCity()) && !"".equalsIgnoreCase(customer.getCity())) {
      customer.setCity(userInfo.getCity());
    }
    if (Objects.nonNull(userInfo.getCity()) && !"".equalsIgnoreCase(customer.getCity())) {
      customer.setCity(userInfo.getCity());
    }
    if (Objects.nonNull(userInfo.getCity()) && !"".equalsIgnoreCase(customer.getCity())) {
      customer.setCity(userInfo.getCity());
    }
    customer.setUpdatedAt();

    return userRepository.save(customer);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

  // Other methods

}
