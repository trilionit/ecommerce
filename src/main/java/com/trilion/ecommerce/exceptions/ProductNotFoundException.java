package com.trilion.ecommerce.exceptions;

public class ProductNotFoundException extends Exception {

  private int code;

  public ProductNotFoundException(int code, String message) {
    super(message);
    this.setCode(code);
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

}
