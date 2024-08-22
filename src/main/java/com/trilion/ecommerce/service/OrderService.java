package com.trilion.ecommerce.service;

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

      // Get Product info to set up order
      int orderQuantity = order.getQuantity();
      double unitPrice = product.getUnitPrice();

      int productQuantity = product.getQuantity();

      // Calculate totalCost
      double totalCost = unitPrice * orderQuantity;

      order.setTotalCost(totalCost);
      orderRepository.save(order);

      // update product Quantity
      int currentQuantity = productQuantity - orderQuantity;
      if (currentQuantity >= 0) {
        product.setQuantity(currentQuantity);
        productRepository.save(product);
      }
    }
    return order;
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

  public void placeOrder(User customer, Product products) {

  }

  // Other service calls
}
