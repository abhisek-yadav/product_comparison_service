package com.rel.pcs.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Product {

  @JsonProperty(value = "product_id")
  private String productID;

  @NotBlank(message = "product_name must not blank")
  @JsonProperty(value = "product_name")
  private String productName;

  @NotBlank(message = "product_category must not blank")
  @JsonProperty(value = "product_category")
  private String productCategory;

  @NotNull(message = "product_price must not null")
  @JsonProperty(value = "product_price")
  private Float productPrice;

}
