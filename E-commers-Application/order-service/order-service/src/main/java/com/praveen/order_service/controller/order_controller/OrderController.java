package com.praveen.order_service.controller.order_controller;

import com.praveen.order_service.dto.request.order_request.OrderRequest;
import com.praveen.order_service.dto.response.OrderResponse;
import com.praveen.order_service.service.order_service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

  private final OrderService service;

  @PostMapping
  public ResponseEntity<Integer> createOrder(
      @RequestBody @Valid OrderRequest request
  ) {
    return ResponseEntity.ok(this.service.createOrder(request));
  }

  @GetMapping
  public ResponseEntity<List<OrderResponse>> findAll() {
    return ResponseEntity.ok(this.service.findAllOrders());
  }

  @GetMapping("/{order-id}")
  public ResponseEntity<OrderResponse> findById(
      @PathVariable("order-id") Integer orderId
  ) {
    return ResponseEntity.ok(this.service.findById(orderId));
  }
}