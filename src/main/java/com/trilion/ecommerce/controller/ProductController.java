package com.trilion.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.exceptions.ProductException;
import com.trilion.ecommerce.service.ProductService;

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
public class ProductController {

  private final ProductService inventoryService;

  ProductController(ProductService inventoryService) {
    this.inventoryService = inventoryService;
  }

  @GetMapping("/inventory")
  public ResponseEntity<List<Product>> getProducts() {
    return new ResponseEntity<>(inventoryService.getAllProducts(), HttpStatus.OK);
  }

  @PostMapping("/inventory/new")
  public ResponseEntity<Product> addProduct(@RequestBody Product product) {
    return new ResponseEntity<>(inventoryService.save(product), HttpStatus.OK);
  }

  @PutMapping("/inventory/{id}")
  public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
    inventoryService.updateProduct(id, product);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @PutMapping("/inventory/product/{id}/quantity/{qty}")
  public ResponseEntity<Product> updateProductQty(@PathVariable Long id, @PathVariable int qty)
      throws ProductException {
    Product product = inventoryService.updateProductQty(id, qty);
    return new ResponseEntity<>(product, HttpStatus.OK);
  }

  @DeleteMapping("/inventory/{id}")
  public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    inventoryService.deleteProduct(id);
    return new ResponseEntity<>("Successfully deleted", HttpStatus.OK);
  }

}
