package com.trilion.ecommerce.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.trilion.ecommerce.status.DeliveryStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "delivery")
public class ShippingAndDelivery {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private DeliveryStatus deliveryStatus;

  private String deliveryCompany;

  private String trackingNumber;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime createdAt;
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  private LocalDateTime updatedAt;

  @ManyToOne
  @JoinColumn(name = "order_id", referencedColumnName = "id")
  private Order order;

  @ManyToOne
  @JoinColumn(name = "customer_address_id", referencedColumnName = "id")
  private Address shippingAddress;

  @ManyToOne
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  private User shipTo;

  @PrePersist
  private void init() {
    setCreatedAt();
    setUpdatedAt();
  }

  public Address getShippingAddress() {
    return shippingAddress;
  }

  public void setShippingAddress(Address shippingAddress) {
    this.shippingAddress = shippingAddress;
  }

  public User getShipTo() {
    return shipTo;
  }

  public void setShipTo(User shipTo) {
    this.shipTo = shipTo;
  }

  public Order getOrder() {
    return order;
  }

  public void setOrder(Order order) {
    this.order = order;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DeliveryStatus getDeliveryStatus() {
    return deliveryStatus;
  }

  public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
    this.deliveryStatus = deliveryStatus;
  }

  public String getDeliveryCompany() {
    return deliveryCompany;
  }

  public void setDeliveryCompany(String deliveryCompany) {
    this.deliveryCompany = deliveryCompany;
  }

  public String getTrackingNumber() {
    return trackingNumber;
  }

  public void setTrackingNumber(String trackingNumber) {
    this.trackingNumber = trackingNumber;
  }

  public void setCreatedAt() {
    this.createdAt = LocalDateTime.now();
  }

  public void setUpdatedAt() {
    this.updatedAt = LocalDateTime.now();
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

}
