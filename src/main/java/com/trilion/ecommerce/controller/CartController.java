package com.trilion.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.trilion.ecommerce.entity.Cart;
import com.trilion.ecommerce.service.CartService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class CartController {

  private final CartService cartService;

  CartController(CartService cartService) {
    this.cartService = cartService;
  }

  @PostMapping("cart/add/user/{userId}/product/{productId}")
  public ResponseEntity<Cart> add(@RequestBody Cart payload, @PathVariable Long userId, @PathVariable Long productId) {
    Cart addToCart = cartService.addToCart(payload, productId, userId);
    return new ResponseEntity<>(addToCart, HttpStatus.OK);
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
