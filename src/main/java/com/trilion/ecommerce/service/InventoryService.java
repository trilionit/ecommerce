package com.trilion.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Inventory;
import com.trilion.ecommerce.exceptions.ProductNotFoundException;
import com.trilion.ecommerce.repository.InventoryRepository;

@Service
public class InventoryService {

  @Autowired
  private InventoryRepository inventoryRepo;

  public Inventory save(Inventory product) {
    return inventoryRepo.save(product);
  }

  public Inventory updateProduct(Long id, Inventory product) {

    return product;
  }

  public Inventory getProduct(Long id) {
    Inventory product = inventoryRepo.findById(id).orElse(null);
    return product;
  }

  public Inventory updateProductQty(Long id, int qty) throws ProductNotFoundException {
    Inventory product = this.getProduct(id);

    if (product != null) {
      product.setQuantity(qty);
      inventoryRepo.save(product);
    } else {
      throw new ProductNotFoundException(404, "Product " + id + " not available");
    }

    return product;

  }

  public List<Inventory> getAllProducts() {
    List<Inventory> products = inventoryRepo.findAll();
    return products;
  }

  public void deleteProduct(Long id) {
    inventoryRepo.deleteById(id);
  }

}
