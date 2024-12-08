package com.mcharolabs.order_service.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


public class OrderLineItemsDto {
    private UUID id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    public OrderLineItemsDto() {
    }

    public OrderLineItemsDto(String skuCode, BigDecimal price, Integer quantity, UUID id) {
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
