package com.praveen.order_service.service.order_service;

import com.praveen.order_service.dto.request.order_request.OrderRequest;
import com.praveen.order_service.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {
    public Integer createOrder(OrderRequest request);

    public List<OrderResponse> findAllOrders();

    public OrderResponse findById(Integer orderId);
}
