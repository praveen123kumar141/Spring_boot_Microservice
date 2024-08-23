package com.praveen.order_service.mapper;

import com.praveen.order_service.dto.request.order_request.OrderRequest;
import com.praveen.order_service.dto.response.OrderResponse;
import com.praveen.order_service.model.order_model.Order;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Service
public class OrderMapper {

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
    public Order toOrder(OrderRequest request) {
        if (request == null) {
            return null;
        }
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .createdDate(new Date())
                .build();
    }
}
