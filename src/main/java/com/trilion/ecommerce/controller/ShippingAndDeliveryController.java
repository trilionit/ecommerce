package com.trilion.ecommerce.controller;

import org.springframework.web.bind.annotation.RestController;

import com.trilion.ecommerce.entity.ShippingAndDelivery;
import com.trilion.ecommerce.repository.ShippingAndDeliveryRepository;
import com.trilion.ecommerce.service.ShippingAndDeliveryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class ShippingAndDeliveryController {

  // private final ShippingAndDeliveryService shipAndDelivery;

  // @PostMapping("ship/order/{order_id}")
  // public ShippingAndDelivery shipOrder(@RequestBody ShippingAndDelivery
  // payload, @PathVariable Long order_id) {

  // }

}
