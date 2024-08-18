package com.trilion.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.Customer;
import com.trilion.ecommerce.service.CustomerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class CustomerController {

  private final CustomerService userService;

  CustomerController(CustomerService userService) {
    this.userService = userService;
  }

  @PostMapping("/customer")
  public ResponseEntity<Customer> newUser(@RequestBody Customer user) {
    return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
  }

  @GetMapping("/customers")
  public ResponseEntity<List<Customer>> getAllCustomers() {
    List<Customer> customers = userService.getAllCustomers();
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<Customer> getUser(@PathVariable Long id) {
    return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
  }

  @PutMapping("customer/{id}")
  public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer user) {
    Customer updateCustomer = userService.updateUser(id, user);
    return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/customer/{id}")
  ResponseEntity<String> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
  }

}
