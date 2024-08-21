package com.trilion.ecommerce.controller;

import java.util.List;

import javax.management.relation.RelationNotFoundException;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.entity.Order;
import com.trilion.ecommerce.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class OrderController {

  private final OrderService orderService;

  OrderController(OrderService orderService) {
    this.orderService = orderService;
  }

  @PostMapping("orders/new/product/{product_id}/user/{user_id}")
  public ResponseEntity<Order> saveOrder(@PathVariable Long user_id, @PathVariable Long product_id,
      @RequestBody Order order)
      throws RelationNotFoundException {

    return new ResponseEntity<>(orderService.save(user_id, product_id, order), HttpStatus.OK);
  }

  @GetMapping("orders/{id}")
  public ResponseEntity<Order> getOrder(@PathVariable Long id) {
    return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
  }

  @GetMapping("orders/user/{id}")
  public ResponseEntity<List<Order>> getCustomerOrders(@PathVariable Long id)
      throws RelationNotFoundException {
    return new ResponseEntity<>(orderService.getCustomerOrders(id),
        HttpStatus.OK);
  }

  @GetMapping("orders/product/{id}")
  public ResponseEntity<List<Order>> getOrdersByProduct(@PathVariable Long id) {
    return new ResponseEntity<List<Order>>(orderService.getOrdersByProductId(id),
        HttpStatus.OK);
  }

  // @GetMapping("/customer/{id}")
  // public ResponseEntity<List<Orders>> getOrdersByUser(@PathVariable Long id) {
  // return new ResponseEntity<>(orderService.getOrdersByUserId(id),
  // HttpStatus.OK);
  // }

}
