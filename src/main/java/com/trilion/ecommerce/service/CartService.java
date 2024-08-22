package com.trilion.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Cart;
import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.repository.CartRepository;
import com.trilion.ecommerce.repository.ProductRepository;
import com.trilion.ecommerce.repository.UserRepository;

@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  UserRepository userRepository;

  public Cart addToCart(Cart cart, Long product_id, Long user_id) {
    Product product = productRepository.findById(product_id).get();
    User user = userRepository.findById(user_id).get();
    if (user != null && product != null) {
      // create Cart Items
      cart.setProduct(product);
      cart.setUser(user);

      cartRepository.save(cart);
    }

    return cart;
  }

  public List<Cart> getAllCartItems() {
    return cartRepository.findAll();
  }

  public List<Cart> getUserCartItems(Long user_id) {
    return cartRepository.getByUserId(user_id);
  }
}
