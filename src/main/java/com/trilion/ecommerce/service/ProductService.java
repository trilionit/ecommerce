package com.trilion.ecommerce.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.exceptions.ProductException;
import com.trilion.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

  @Autowired
  private ProductRepository productRepo;

  public Product save(Product product) {
    Product productExits = productRepo.findByProductNameAndCategoryAndSubCategory(product.getProductName(),
        product.getCategory(), product.getSubCategory());
    if (productExits == null) {
      productRepo.save(product);
    } else {
      this.updateProduct(productExits.getId(), product);
    }
    return product;
  }

  public Product updateProduct(Long id, Product product) {
    Product updateProduct = productRepo.findById(id).get();
    if (Objects.nonNull(product.getProductName()) && !"".equalsIgnoreCase(updateProduct.getProductName())) {
      updateProduct.setProductName(product.getProductName());
    }
    if (Objects.nonNull(product.getCategory()) && !"".equals(updateProduct.getCategory())) {
      updateProduct.setCategory(product.getCategory());
    }
    if (Objects.nonNull(product.getSubCategory()) & !"".equals(updateProduct.getSubCategory())) {
      updateProduct.setSubCategory(product.getSubCategory());
    }
    if (Objects.nonNull(product.getQuantity()) & !"".equals(updateProduct.getQuantity())) {
      updateProduct.setQuantity(product.getQuantity());
    }
    if (Objects.nonNull(product.getUnitPrice()) & !"".equals(updateProduct.getUnitPrice())) {
      updateProduct.setUnitPrice(product.getUnitPrice());
    }

    updateProduct.setUpdatedAt();
    productRepo.save(updateProduct);

    return updateProduct;
  }

  public Product updateProductQty(Long id, int qty) throws ProductException {
    Product product = this.getProduct(id);

    if (product != null && qty > 0) {
      product.setQuantity(qty);
      productRepo.save(product);
    } else {
      throw new ProductException(404, "Product " + id + " not available");
    }

    return product;

  }

  public Product getProduct(Long id) {
    Product product = productRepo.findById(id).orElse(null);
    return product;
  }

  public List<Product> getAllProducts() {
    List<Product> products = productRepo.findAll();
    return products;
  }

  public void deleteProduct(Long id) {
    productRepo.deleteById(id);
  }

}
