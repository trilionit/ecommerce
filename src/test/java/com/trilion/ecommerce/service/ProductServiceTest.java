package com.trilion.ecommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Optional;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import org.springframework.boot.test.context.SpringBootTest;

import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.repository.ProductRepository;

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductServiceTest {

  @Mock
  public ProductRepository productRepo;

  @InjectMocks
  private ProductService productService;

  @BeforeEach
  public void setMockOutput() {
    MockitoAnnotations.openMocks(this);
  }

  // Mock Data
  String productName = "Logitec Mouse";
  String category = "Electronics";
  String subCategory = "Computers";
  int quantity = 10;
  double unitPrice = 100;

  @Test
  void testSave() {
    Product product = new Product();
    product.setProductName(productName);
    product.setCategory(category);
    product.setSubCategory(subCategory);
    product.setQuantity(quantity);
    product.setUnitPrice(unitPrice);

    when(productRepo.save(product)).thenReturn(product);

    Product savedProduct = productService.save(product);

    verify(productRepo, times(1)).save(product);
    assertEquals(product, savedProduct);

  }

  @Test
  void testUpdateProduct() {
    Product existingProduct = new Product();
    existingProduct.setProductName(productName);
    existingProduct.setCategory(category);
    existingProduct.setSubCategory(subCategory);
    existingProduct.setQuantity(quantity);
    existingProduct.setUnitPrice(unitPrice);

    Product updateProduct = new Product();
    updateProduct.setProductName("New Name");
    updateProduct.setQuantity(10);
    updateProduct.setUnitPrice(450);

    when(productRepo.findById(1L)).thenReturn(Optional.of(existingProduct));
    when(productRepo.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));

    Product updatedProduct = productService.updateProduct(1L, updateProduct);

    assertEquals("New Name", updatedProduct.getProductName());
    assertEquals(450, updatedProduct.getUnitPrice());
    assertEquals(10, updatedProduct.getQuantity());

  }

  @Test
  void testUpdateProductQty() {
    Product product = new Product();
    product.setQuantity(quantity);

    when(productRepo.save(product)).thenReturn(product);

    Product savedProduct = productService.save(product);

    verify(productRepo, times(1)).save(product);
    assertEquals(product, savedProduct);
  }

  @Test
  void testGetAllProducts() {
    List<Product> products = new ArrayList();
    Product p1 = new Product();
    p1.setProductName(productName);
    p1.setCategory(category);
    p1.setSubCategory(subCategory);
    p1.setQuantity(quantity);
    p1.setUnitPrice(unitPrice);

    Product p2 = new Product();
    p2.setProductName(productName);
    p2.setCategory(category);
    p2.setSubCategory(subCategory);
    p2.setQuantity(quantity);
    p2.setUnitPrice(unitPrice);

    products.add(p1);
    products.add(p2);

    when(productRepo.findAll()).thenReturn(products);
    List<Product> expected = productService.getAllProducts();
    assertEquals(expected, products);
  }

  @Test
  void testGetProduct() {
    Long id = 10L;

    Product product = new Product();
    product.setProductName(productName);
    product.setCategory(category);
    product.setSubCategory(subCategory);
    product.setQuantity(quantity);
    product.setUnitPrice(unitPrice);

    when(productRepo.findById(id)).thenReturn(Optional.of(product));

    Product result = productService.getProduct(id);

    assertEquals(product.getProductName(), result.getProductName());
    assertEquals(product, result);
  }

  @Test
  void testDeleteProduct() {
    Long productId = 10L;

    productService.deleteProduct(productId);
    Mockito.verify(productRepo).deleteById(productId);
  }

}
