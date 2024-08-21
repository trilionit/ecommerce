package com.trilion.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.entity.Order;
import com.trilion.ecommerce.repository.UserRepository;
import com.trilion.ecommerce.repository.OrderRepository;
import com.trilion.ecommerce.repository.ProductRepository;

import jakarta.persistence.EntityManager;

@Service
public class OrderService {

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private UserRepository customerRepository;

  @Autowired
  private ProductRepository productRepository;

  public Order save(Long user_id, Long product_id, Order order) throws RelationNotFoundException {
    // get user
    User user = customerRepository.findById(user_id).get();
    if (user != null) {
      Product product = productRepository.findById(product_id).orElse(null);
      if (product != null) {
        order.setProduct(product);
      }
      order.setUser(user);
      orderRepository.save(order);

      // user.setOrders(order);
      customerRepository.save(user);
    }
    return order;

    // Orders saveCustomerOrder = customerRepository.findById(user_id).map(user -> {

    // Products product = productRepository.findById(product_id).orElse(null);
    // if (product != null) {
    // order.setProducts(product);
    // }

    // order.setCustomer(user);
    // return orderRepository.save(order);

    // }).orElseThrow(() -> new RelationNotFoundException("no user found"));

    // return saveCustomerOrder;
    // return orderRepository.save(order);

    // Customer user = customerRepository.findById(user_id)
    // .orElseThrow(() -> new RelationNotFoundException("no user found"));

    // orderRepository.save(order);
    // List<Orders> userOrders = new ArrayList<>();
    // userOrders.add(order);
    // user.setOrders(userOrders);

    // return order;
  }

  public Order getOrderById(Long id) {
    return orderRepository.findById(id).orElse(null);
  }

  public List<Order> getCustomerOrders(Long userId) throws RelationNotFoundException {
    if (!customerRepository.existsById(userId)) {
      throw new RelationNotFoundException("User not found with id: " + userId);
    }
    return orderRepository.findByUserId(userId);
  }

  public List<Order> getOrdersByProductId(Long id) {
    return orderRepository.findByProductId(id);
  }

  // public List<Orders> getOrdersByUserId(Long id) {
  // return orderRepository.findByUserId(id);
  // }

  public void placeOrder(User customer, Product products) {

  }

  // Other service calls
}
