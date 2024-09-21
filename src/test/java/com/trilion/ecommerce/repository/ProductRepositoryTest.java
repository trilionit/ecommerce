package com.trilion.ecommerce.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.trilion.ecommerce.entity.Product;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

  @Autowired
  private TestEntityManager entityManager;

  @Autowired
  private ProductRepository productRepo;

  // Mock Data
  Long id = (long) 1;
  String productName = "Logitec Mouse";
  String category = "Electronics";
  String subCategory = "Computers";
  int quantity = 10;
  double unitPrice = 100;

  @Test
  void testSaveData() {
    Product product = new Product();
    product.setProductName(productName);
    product.setCategory(category);
    product.setSubCategory(subCategory);
    product.setQuantity(quantity);
    product.setUnitPrice(quantity);
    productRepo.save(product);

    Product savedProduct = entityManager.find(Product.class, product.getId());
    assertTrue(savedProduct.getProductName() == productName);

  }
}
