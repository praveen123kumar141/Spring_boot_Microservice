package com.praveen.order_service.service;

import com.praveen.order_service.dto.OrderRequest;

public interface OrderService {
    public String placeOrder(OrderRequest orderRequest);
}
