package com.trilion.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.Orders;
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

  @PostMapping("/orders/new")
  public ResponseEntity<Orders> saveOrder(@RequestBody Orders order) {
    return new ResponseEntity<>(orderService.save(order), HttpStatus.OK);
  }

  @GetMapping("/orders/orderId/{id}")
  public ResponseEntity<Orders> getOrder(@PathVariable Long id) {
    return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
  }

  // @GetMapping("/orders/product/{id}")
  // public ResponseEntity<List<Orders>> getOrdersByProduct(@PathVariable Long id)
  // {
  // return new
  // ResponseEntity<List<Orders>>(orderService.getOrdersByProductId(id),
  // HttpStatus.OK);
  // }

  // @GetMapping("/orders/customer/{id}")
  // public ResponseEntity<List<Orders>> getOrdersByUser(@PathVariable Long id) {
  // return new ResponseEntity<>(orderService.getOrdersByUserId(id),
  // HttpStatus.OK);
  // }

}
