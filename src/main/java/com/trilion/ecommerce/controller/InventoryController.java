package com.trilion.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.Inventory;
import com.trilion.ecommerce.exceptions.ProductNotFoundException;
import com.trilion.ecommerce.service.InventoryService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class InventoryController {

  private final InventoryService inventoryService;

  InventoryController(InventoryService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping("/inventory")
  public ResponseEntity<List<Inventory>> getProducts() {
    return new ResponseEntity<>(inventoryService.getAllProducts(), HttpStatus.OK);
  }

  @PostMapping("/inventory/new")
  public ResponseEntity<Inventory> addProduct(@RequestBody Inventory product) {
    return new ResponseEntity<>(inventoryService.save(product), HttpStatus.OK);
  }

  @PutMapping("/inventory/{id}")
  public ResponseEntity<Inventory> updateProduct(@PathVariable Long id, @RequestBody Inventory product) {
    inventoryService.updateProduct(id, product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PutMapping("/inventory/product/{id}/quantity/{qty}")
  public ResponseEntity<Inventory> updateProductQty(@PathVariable Long id, @PathVariable int qty)
      throws ProductNotFoundException {
    Inventory product = inventoryService.updateProductQty(id, qty);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @DeleteMapping("/inventory/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    inventoryService.deleteProduct(id);
    return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
  }

}
