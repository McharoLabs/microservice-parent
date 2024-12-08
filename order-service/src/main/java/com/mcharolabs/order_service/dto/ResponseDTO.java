package com.mcharolabs.order_service.dto;

import com.mcharolabs.order_service.model.OrderLineItems;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


public class ResponseDTO {
    private UUID id;
    private String orderNumber;
    private List<OrderLineItems> orderLineItemsList;
    private BigDecimal totalPrice;
    private BigDecimal totalItems;

    public ResponseDTO() {
    }

    public ResponseDTO(UUID id, String orderNumber, List<OrderLineItems> orderLineItemsList, BigDecimal totalPrice, BigDecimal totalItems) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.orderLineItemsList = orderLineItemsList;
        this.totalPrice = totalPrice;
        this.totalItems = totalItems;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public List<OrderLineItems> getOrderLineItemsList() {
        return orderLineItemsList;
    }

    public void setOrderLineItemsList(List<OrderLineItems> orderLineItemsList) {
        this.orderLineItemsList = orderLineItemsList;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(BigDecimal totalItems) {
        this.totalItems = totalItems;
    }
}
