package com.trilion.ecommerce.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.Address;
import com.trilion.ecommerce.service.AddressService;
import com.trilion.ecommerce.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class AddressController {

  @Autowired
  AddressService addressService;

  @PostMapping("address/new/user/{user_id}")
  public ResponseEntity<Address> addAddress(@RequestBody Address address, @PathVariable Long user_id) {

    return new ResponseEntity<>(addressService.addAddress(address, user_id), HttpStatus.OK);
  }

  @GetMapping("address")
  public ResponseEntity<List<Address>> getAddresses() {

    return new ResponseEntity<>(addressService.getAddresses(), HttpStatus.OK);
  }

  @GetMapping("address/id/{id}")
  public ResponseEntity<Address> getAddress(@PathVariable Long id) {
    return new ResponseEntity<>(addressService.getAddressById(id), HttpStatus.OK);
  }

  @GetMapping("address/user/{user_id}")
  public ResponseEntity<List<Address>> getUserAddresses(@PathVariable Long user_id) {
    return new ResponseEntity<>(addressService.getUserAddress(user_id), HttpStatus.OK);
  }

  @PutMapping("address/id/{id}")
  public ResponseEntity<Address> update(@PathVariable Long id, @RequestBody Address address) {
    return new ResponseEntity<>(addressService.updateUserAddress(id, address), HttpStatus.OK);
  }

}
