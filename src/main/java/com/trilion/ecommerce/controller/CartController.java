package com.trilion.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import com.trilion.ecommerce.entity.Cart;
import com.trilion.ecommerce.entity.Order;
import com.trilion.ecommerce.exceptions.ProductException;
import com.trilion.ecommerce.service.cart.CartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CartController {

  private final CartService cartService;

  CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @PostMapping("cart/add/user/{userId}/product/{productId}")
  public ResponseEntity<Cart> add(@RequestBody Cart payload, @PathVariable Long userId, @PathVariable Long productId)
      throws ProductException {
    Cart addToCart = cartService.addToCart(payload, productId, userId);
    return new ResponseEntity<>(addToCart, HttpStatus.OK);
  }

  @PostMapping("cart/place-order/user/{userId}")
  public ResponseEntity<String> placeOrder(@RequestBody Cart payload, @PathVariable Long userId)
      throws RelationNotFoundException {
    String addToCart = cartService.placeOrderFromCart(payload, userId);
    return new ResponseEntity<>(addToCart, HttpStatus.OK);
  }

  @PostMapping("cart/remove/user/{user_id}/product/{product_id}")
  public ResponseEntity<String> removeItemFromCart(@RequestBody Cart payload, @PathVariable Long user_id,
      @PathVariable Long product_id) {
    String removeFromCart = cartService.removeFromCart(payload, user_id, product_id);
    return new ResponseEntity<>(removeFromCart, HttpStatus.OK);
  }

  @GetMapping("cart")
  public ResponseEntity<List<Cart>> getAllItems() {
    return new ResponseEntity<>(cartService.getAllCartItems(), HttpStatus.OK);
  }

  @GetMapping("cart/user/{user_id}")
  public ResponseEntity<List<Cart>> getItemsByUser(@PathVariable Long user_id) {
    return new ResponseEntity<>(cartService.getUserCartItems(user_id), HttpStatus.OK);
  }

}
