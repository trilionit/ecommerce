package com.trilion.ecommerce.service.cart;

import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.entity.User;

public class CartItem {

  private final Product product;
  private final User user;
  private int quantity;

  public CartItem(Product product, User user, int quantity) {
    this.product = product;
    this.user = user;
    this.quantity = quantity;
  }

  public Product getProduct() {
    return product;
  }

  public User getUser() {
    return user;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

}
