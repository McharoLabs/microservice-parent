package com.mcharolabs.order_service.service;

import com.mcharolabs.order_service.dto.OrderLineItemsDto;
import com.mcharolabs.order_service.dto.OrderRequest;
import com.mcharolabs.order_service.dto.ResponseDTO;
import com.mcharolabs.order_service.model.Order;
import com.mcharolabs.order_service.model.OrderLineItems;
import com.mcharolabs.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void placeOrder(OrderRequest orderRequest) {
        /**
        try {
            List<OrderLineItems> orderLineItems = orderRequest
                    .getOrderLineItemsDtoList()
                    .stream()
                    .map(this::mapToOrderLineItems)
                    .toList();

            Order order = Order
                    .builder()
                    .orderNumber(UUID.randomUUID().toString())
                    .orderLineItemsList(orderLineItems)
                    .build();

            orderRepository.save(order);
            log.info("Order is saved: {}", order);
        } catch (Exception e) {
            log.error("Error occurred while creating order: {}", e.getMessage(), e);
            throw new RuntimeException("An error occurred while placing the order: " + e.getMessage(), e);
        }
         */
    }

    private OrderLineItems mapToOrderLineItems(OrderLineItemsDto item) {
        OrderLineItems orderLineItem = new OrderLineItems();
        orderLineItem.setPrice(item.getPrice());
        orderLineItem.setSkuCode(item.getSkuCode());
        orderLineItem.setQuantity(item.getQuantity());

        return orderLineItem;
    }

    public List<ResponseDTO> getAllOrder() {
        try {
            List<Order> orders = orderRepository.findAll();

            return orders
                    .stream()
                    .map(this::mapToResponseDTO)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while fetching all orders" + e.getMessage(), e);
        }
    }

    private ResponseDTO mapToResponseDTO(Order order) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setId(order.getId());
        responseDTO.setOrderNumber(order.getOrderNumber());
        responseDTO.setOrderLineItemsList(order.getOrderLineItemsList());
        responseDTO.setTotalPrice(order.getTotalPrice());
        responseDTO.setTotalItems(new BigDecimal(order.getTotalItems()));

        return responseDTO;
    }
}
