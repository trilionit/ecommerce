package com.trilion.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.exceptions.ProductException;
import com.trilion.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository inventoryRepo;

  public Product save(Product product) {
    return inventoryRepo.save(product);
  }

  public Product updateProduct(Long id, Product product) {

    return product;
  }

  public Product getProduct(Long id) {
    Product product = inventoryRepo.findById(id).orElse(null);
    return product;
  }

  public Product updateProductQty(Long id, int qty) throws ProductException {
    Product product = this.getProduct(id);

    if (product != null && qty > 0) {
      product.setQuantity(qty);
      inventoryRepo.save(product);
    } else {
      throw new ProductException(404, "Product " + id + " not available");
    }

    return product;

  }

  public List<Product> getAllProducts() {
    List<Product> products = inventoryRepo.findAll();
    return products;
  }

  public void deleteProduct(Long id) {
    inventoryRepo.deleteById(id);
  }

}
