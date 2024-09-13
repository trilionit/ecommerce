package com.trilion.ecommerce.service;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.trilion.ecommerce.entity.Address;
import com.trilion.ecommerce.entity.Order;
import com.trilion.ecommerce.entity.ShippingAndDelivery;
import com.trilion.ecommerce.entity.User;
import com.trilion.ecommerce.repository.AddressRepository;
import com.trilion.ecommerce.repository.OrderRepository;
import com.trilion.ecommerce.repository.ShippingAndDeliveryRepository;
import com.trilion.ecommerce.status.DeliveryStatus;

@Service
public class ShippingAndDeliveryService {

  @Autowired
  private ShippingAndDeliveryRepository deliveryRepository;

  @Autowired
  private OrderRepository orderRepository;

  @Autowired
  private AddressRepository addressRepository;

  @Autowired
  private RestClient restClient;

  @Autowired
  OrderService orderService;

  public String save(ShippingAndDelivery delivery, Long order_id) throws IOException {

    Order order = orderRepository.findById(order_id).get();
    System.out.println("Delivery Company: " + delivery.getDeliveryCompany());
    if (order != null) {
      User user = order.getUser();
      Long user_id = user.getId();

      // Get Address associated with User
      Address mailingAddress = addressRepository.getByUserIdAndPrimaryAddress(user_id, true);

      String mailingInfo = this.getMailingInfo(mailingAddress, user);

      // Save to DB
      delivery.setOrder(order);
      delivery.setShipTo(user);
      delivery.setShippingAddress(mailingAddress);
      delivery.setDeliveryCompany(delivery.getDeliveryCompany());
      deliveryRepository.save(delivery);

      // send to third pary
      String response = restClient.sendRequest("POST", "http://localhost:8082/fedex/mail/order", mailingInfo);
      this.getMailingResponse(response, delivery.getId());

    }
    return "successful";
  }

  private void getMailingResponse(String response, Long id) {
    ObjectMapper objectMapper = new ObjectMapper();
    ShippingAndDelivery delivery = deliveryRepository.findById(id).get();

    try {
      ShippingAndDelivery packageInfo = objectMapper.readValue(response, ShippingAndDelivery.class);
      if (delivery != null) {
        delivery.setDeliveryStatus(packageInfo.getDeliveryStatus());
        delivery.setTrackingNumber(packageInfo.getTrackingNumber());
        deliveryRepository.save(delivery);
      } else {
        System.out.println("Cannot find Shipping Info");
      }

    } catch (JsonProcessingException e) {
      // Handle exceptions
      e.printStackTrace();
    }
  }

  private String getMailingInfo(Address address, User user) throws JsonProcessingException {

    ObjectMapper objectMapper = new ObjectMapper();

    // Create a simple JSON object
    Map<String, Object> jsonObject = new HashMap<>();
    jsonObject.put("firstName", user.getFirstName());
    jsonObject.put("lastName", user.getLastName());
    jsonObject.put("address", address.getAddress());
    jsonObject.put("city", address.getCity());
    jsonObject.put("state", address.getState());
    jsonObject.put("zipcode", address.getZipcode());
    jsonObject.put("mailer", "localStore");

    // Convert the map to a JSON string
    String jsonString = objectMapper.writeValueAsString(jsonObject);

    return jsonString;
  }

  public String deliveryStatus(Long id, int status) {
    ShippingAndDelivery shippingInfo = deliveryRepository.findById(id).get();
    DeliveryStatus ds = DeliveryStatus.LABEL_CREATED;
    String response = "not succesful";

    if (shippingInfo != null) {
      switch (status) {
        case 0:
          ds = DeliveryStatus.LABEL_CREATED;
          break;
        case 1:
          ds = DeliveryStatus.SHIPPED;
          break;
        case 2:
          ds = DeliveryStatus.DELIVERED;
          break;
        case 3:
          ds = DeliveryStatus.NOT_DELIVERED;
          break;
        case 4:
          ds = DeliveryStatus.LABEL_NOT_CREATED;
          break;
        default:
          ds = DeliveryStatus.LABEL_CREATED;
      }
      if (ds == DeliveryStatus.NOT_DELIVERED) {
        Long orderId = shippingInfo.getOrder().getId();
        response = orderService.updateProductOrderWhenNotDelivered(orderId);
      }
      shippingInfo.setDeliveryStatus(ds);
      deliveryRepository.save(shippingInfo);
      response = "successful";
    }
    return response;
  }

}
