package com.mcharolabs.order_service.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "T_order_line_items")
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;

    @ManyToOne()
    @JoinColumn(referencedColumnName = "id")
    private Order order;

    public OrderLineItems() {
    }

    public OrderLineItems(UUID id, String skuCode, BigDecimal price, Integer quantity, Order order) {
        this.id = id;
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
        this.order = order;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
