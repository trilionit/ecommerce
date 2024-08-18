package com.trilion.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Customer;
import com.trilion.ecommerce.entity.Inventory;
import com.trilion.ecommerce.entity.Orders;
import com.trilion.ecommerce.repository.OrderRepository;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  public Orders save(Orders order) {
    return orderRepository.save(order);
  }

  public Orders getOrderById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  // public List<Orders> getOrdersByProductId(Long id) {
  // return orderRepository.findByProductId(id);
  // }

  // public List<Orders> getOrdersByUserId(Long id) {
  // return orderRepository.findByUserId(id);
  // }

  public void placeOrder(Customer customer, Inventory products) {

  }

  // Other service calls
}
