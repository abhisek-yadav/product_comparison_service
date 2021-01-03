package com.rel.pcs.util;

public class Constants {

  private Constants() {
    // no-implementation
    throw new IllegalStateException("Constants class");
  }

  public static final String GET_PRODUCT = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID = ?";
  public static final String GET_ALL_PRODUCTS_WITH_NAME_AND_CATEGORY = "SELECT * FROM PRODUCTS WHERE PRODUCT_NAME LIKE '%s%s%s' AND PRODUCT_CATEGORY LIKE '%s%s%s'";
  public static final String CREATE_PRODUCT = "INSERT INTO PRODUCTS (PRODUCT_ID, PRODUCT_NAME, PRODUCT_CATEGORY, PRODUCT_PRICE) VALUES (?,?,?,?)";
  public static final String UPDATE_PRODUCT = "UPDATE PRODUCTS SET PRODUCT_NAME = ?, PRODUCT_CATEGORY = ?, PRODUCT_PRICE = ? WHERE PRODUCT_ID = ?";
  public static final String DELETE_PRODUCT = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";

}
