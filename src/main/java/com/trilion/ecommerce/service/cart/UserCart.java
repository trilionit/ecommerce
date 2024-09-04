package com.trilion.ecommerce.service.cart;

import java.util.Map;

import com.trilion.ecommerce.entity.Product;
import com.trilion.ecommerce.entity.User;

import java.util.HashMap;

public class UserCart {

  private final Map<Long, CartItem> items = new HashMap<>();

  public void addProduct(User user, Product product, int quantity) {

    if (quantity <= 0 || product == null || user == null) {
      return;
    }

    CartItem item = items.get(product.getId());
    if (item == null) {
      items.put(product.getId(), new CartItem(product, user, quantity));
    } else {
      item.setQuantity(item.getQuantity() + quantity);
    }

  }
}
