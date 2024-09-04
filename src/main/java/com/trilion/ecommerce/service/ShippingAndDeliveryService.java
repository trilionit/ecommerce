package com.trilion.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Order;
import com.trilion.ecommerce.entity.ShippingAndDelivery;
import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.repository.OrderRepository;
import com.trilion.ecommerce.repository.ShippingAndDeliveryRepository;
import com.trilion.ecommerce.repository.UserRepository;

@Service
public class ShippingAndDeliveryService {

  @Autowired
  private ShippingAndDeliveryRepository deliveryRepository;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private OrderRepository orderRepository;

  // public ShippingAndDelivery save(ShippingAndDelivery delivery, Long order_id)
  // {
  // Order order = orderRepository.findById(order_id).orElse(null);
  // if (order != null) {
  // User user = order.getUser();
  // Long user_id = user.getId();
  // }
  // // User user = userRepository.findById(user_id).orElse(null);
  // }

}
