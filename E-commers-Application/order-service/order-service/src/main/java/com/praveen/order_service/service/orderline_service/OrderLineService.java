package com.praveen.order_service.service.orderline_service;

import com.praveen.order_service.dto.request.orderline_request.OrderLineRequest;
import com.praveen.order_service.dto.response.OrderLineResponse;

import java.util.List;

public interface OrderLineService {

  public List<OrderLineResponse> findAllByOrderId(Integer orderId);
  public Integer saveOrderLine(OrderLineRequest request);
}
