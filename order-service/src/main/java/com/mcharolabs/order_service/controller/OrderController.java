package com.mcharolabs.order_service.controller;

import com.mcharolabs.order_service.dto.OrderRequest;
import com.mcharolabs.order_service.dto.OrderResponse;
import com.mcharolabs.order_service.dto.ResponseDTO;
import com.mcharolabs.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse<Object>> placeOrder(@RequestBody OrderRequest orderRequest) {
        try {
            orderService.placeOrder(orderRequest);
            OrderResponse<Object> response = new OrderResponse<>("success", "Order created successfully.", null);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            OrderResponse<Object> errorMessage = new OrderResponse<>("error", "An error occurred while creating order: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
    }

    @GetMapping
    public ResponseEntity<OrderResponse<List<ResponseDTO>>> getAllOrder() {
        try {
            List<ResponseDTO> orders = orderService.getAllOrder();
            OrderResponse<List<ResponseDTO>> response = new OrderResponse<>("success", "Order retrieved successful", orders);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (Exception e) {
            OrderResponse<List<ResponseDTO>> errorResponse = new OrderResponse<>("error", "An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}
