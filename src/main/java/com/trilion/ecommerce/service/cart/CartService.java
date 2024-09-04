package com.trilion.ecommerce.service.cart;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import javax.management.relation.RelationNotFoundException;

import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Cart;
import com.trilion.ecommerce.entity.Order;
import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.exceptions.ProductException;
import com.trilion.ecommerce.repository.CartRepository;
import com.trilion.ecommerce.repository.OrderRepository;
import com.trilion.ecommerce.repository.ProductRepository;
import com.trilion.ecommerce.repository.UserRepository;

import com.trilion.ecommerce.service.OrderService;

import jakarta.transaction.Transactional;

@Service
public class CartService {

  @Autowired
  CartRepository cartRepository;

  @Autowired
  ProductRepository productRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  OrderRepository orderRepository;

  public Cart addToCart(Cart cart, Long product_id, Long user_id) throws ProductException {

    Product product = productRepository.findById(product_id).get();
    User user = userRepository.findById(user_id).get();

    if (product.getQuantity() < cart.getQuantity()) {
      throw new ProductException(404, "Exceeded Avaialable Product Quantity");
    }

    if (user != null && product != null) {
      Cart userCart = cartRepository.getByUserIdAndProductId(user_id, product_id);

      if (userCart != null) {
        this.updateCartItems(cart, userCart.getId());
      } else {
        this.addCartItems(cart, product, user);
      }
    }

    int currentQuantity = product.getQuantity() - cart.getQuantity();
    product.setQuantity(currentQuantity <= 0 ? 0 : currentQuantity);
    productRepository.save(product);
    return cart;
  }

  private Cart addCartItems(Cart cart, Product product, User user) {
    cart.setQuantity(cart.getQuantity());
    cart.setProduct(product);
    cart.setUser(user);
    cartRepository.save(cart);

    return cart;
  }

  public Cart updateCartItems(Cart cart, Long cartId) {
    Cart cartItem = cartRepository.findById(cartId).orElse(null);
    if (cartItem != null) {
      cartItem.setQuantity(cartItem.getQuantity() + cart.getQuantity());
    }
    cartRepository.save(cartItem);
    return cartItem;
  }

  public List<Cart> getAllCartItems() {
    return cartRepository.findAll();
  }

  public List<Cart> getUserCartItems(Long user_id) {
    return cartRepository.getByUserId(user_id);
  }

  @Transactional
  public String placeOrderFromCart(Cart payload, Long userId) throws RelationNotFoundException {
    List<Cart> carts = cartRepository.getByUserId(userId);

    for (Cart cart : carts) {
      Order order = new Order();

      order.setUser(cart.getUser());

      Product product = cart.getProduct();
      order.setProduct(product);

      int orderQuantity = cart.getQuantity();
      order.setQuantity(orderQuantity);

      // Get Product info to set up order

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

      System.out.println("Current Order: " + order.getProduct().getProductName());

      cartRepository.delete(cart);
    }
    return "successful";
  }

  @Transactional
  public String removeFromCart(Cart payload, Long user_id, Long product_id) {
    List<Cart> cartItems = cartRepository.getByUserId(user_id);
    int quantity = payload.getQuantity();
    String response = "";
    // find the product
    for (Cart item : cartItems) {
      Product cartItem = item.getProduct();
      Long cartItemProductId = cartItem.getId();

      if (cartItemProductId == product_id) {
        item.setQuantity(item.getQuantity() - quantity);
        cartRepository.save(item);

        Product product = productRepository.getReferenceById(product_id);
        int currentProductQty = product.getQuantity();

        product.setQuantity(currentProductQty + quantity);
        productRepository.save(product);

        if (cartItem.getQuantity() == 0) {
          cartRepository.delete(item);
        }
        response = "successful";
      } else {
        response = "not successful";
      }

    }
    return response;
  }
}
