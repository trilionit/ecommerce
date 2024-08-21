package com.trilion.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.service.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

  private final UserService userService;

  UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/customers")
  public ResponseEntity<List<User>> getAllCustomers() {
    List<User> customers = userService.getAllCustomers();
    return new ResponseEntity<>(customers, HttpStatus.OK);
  }

  @GetMapping("/customer/{id}")
  public ResponseEntity<User> getUser(@PathVariable Long id) {
    return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
  }

  @GetMapping("/customer/email/{email}")
  public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
    return new ResponseEntity<>(userService.getUserByEmail(email), HttpStatus.OK);
  }

  @PostMapping("/customer")
  public ResponseEntity<User> newUser(@RequestBody User user) {
    return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
  }

  @PutMapping("customer/{id}")
  public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
    User updateCustomer = userService.updateUser(id, user);
    return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
  }

  @DeleteMapping("/customer/{id}")
  ResponseEntity<String> deleteUser(@PathVariable Long id) {
    userService.deleteUser(id);
    return new ResponseEntity<>("Successfully Deleted", HttpStatus.OK);
  }

}
