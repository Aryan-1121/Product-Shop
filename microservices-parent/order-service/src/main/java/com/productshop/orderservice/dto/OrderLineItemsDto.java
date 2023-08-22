package com.productshop.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


 
public class OrderLineItemsDto {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
