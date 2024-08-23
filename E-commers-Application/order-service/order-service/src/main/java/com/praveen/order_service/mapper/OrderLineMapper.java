package com.praveen.order_service.mapper;


import com.praveen.order_service.dto.request.orderline_request.OrderLineRequest;
import com.praveen.order_service.dto.response.OrderLineResponse;
import com.praveen.order_service.model.order_model.Order;
import com.praveen.order_service.model.orderline_model.OrderLine;
import org.springframework.stereotype.Service;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}
