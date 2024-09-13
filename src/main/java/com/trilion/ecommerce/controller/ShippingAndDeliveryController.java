package com.trilion.ecommerce.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.ShippingAndDelivery;
import com.trilion.ecommerce.service.ShippingAndDeliveryService;
import com.trilion.ecommerce.status.DeliveryStatus;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ShippingAndDeliveryController {

  private final ShippingAndDeliveryService shipAndDelivery;

  ShippingAndDeliveryController(ShippingAndDeliveryService ship) {
    this.shipAndDelivery = ship;
  }

  @PostMapping("ship/order/{order_id}")
  public ResponseEntity<String> shipOrder(@RequestBody ShippingAndDelivery payload, @PathVariable Long order_id)
      throws IOException {

    String response = shipAndDelivery.save(payload, order_id);
    // Send Response to Third Party API then await response;
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PutMapping("ship/{deliveryId}/status/{status}")
  public ResponseEntity<String> deliveryStatus(@PathVariable Long deliveryId, @PathVariable int status)
      throws IOException {

    String response = shipAndDelivery.deliveryStatus(deliveryId, status);
    // Send Response to Third Party API then await response;
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

}
