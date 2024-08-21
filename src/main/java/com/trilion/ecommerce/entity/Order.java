package com.trilion.ecommerce.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // private Long productId;
  // private Long userId;
  private int quantity;
  private double totalCost;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  // @JsonIgnore
  private User user;

  @ManyToOne
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  public Order() {
  }

  public Order(int quantity, double totalCost) {
    this.quantity = quantity;
    this.totalCost = totalCost;
  }
  // Getters and Setters

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @PrePersist
  private void init() {
    setCreatedAt();
    setUpdatedAt();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  // public Long getProductId() {
  // return productId;
  // }

  // public void setProductId(Long productId) {
  // this.productId = productId;
  // }

  // public Long getUserId() {
  // return userId;
  // }

  // public void setUserId(Long userId) {
  // this.userId = userId;
  // }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public double getTotalCost() {
    return totalCost;
  }

  public void setTotalCost(int totalCost) {
    this.totalCost = totalCost;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

}
