package com.trilion.ecommerce.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.ResultActions;

import com.trilion.ecommerce.entity.Address;
import com.trilion.ecommerce.service.AddressService;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(AddressController.class)
public class AddressControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  AddressService addressService;

  @Test
  public void testAddAddress() throws Exception {

    ResultActions response = mockMvc.perform(get("/addres"));

    response
        .andExpect(status().isOk());
    // .andExpect(content().string("expected response"));
  }
}
