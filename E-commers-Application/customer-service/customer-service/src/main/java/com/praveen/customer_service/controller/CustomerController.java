package com.praveen.customer_service.controller;


import com.praveen.customer_service.dto.CustomerRequest;
import com.praveen.customer_service.dto.CustomerResponse;
import com.praveen.customer_service.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(" n ")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService service;

  @PostMapping
  public ResponseEntity<CustomerResponse> createCustomer(@RequestBody @Valid CustomerRequest request) {
    return ResponseEntity.ok(this.service.createCustomer(request));
  }

  @PutMapping
  public ResponseEntity<String> updateCustomer(@RequestBody @Valid CustomerRequest request) {
    String response = this.service.updateCustomer(request);
    return ResponseEntity.accepted().body(response);
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> findAll() {

    return ResponseEntity.ok(this.service.findAllCustomers());
  }

  @GetMapping("/exists/{customer-id}")
  public ResponseEntity<Boolean> existsById(@PathVariable("customer-id") Long customerId) {
    return ResponseEntity.ok(this.service.existsById(customerId));
  }

  @GetMapping("/{customer-id}")
  public ResponseEntity<CustomerResponse> findById(@PathVariable("customer-id") Long customerId) {
    return ResponseEntity.ok(this.service.findById(customerId));
  }

  @DeleteMapping("/{customer-id}")
  public ResponseEntity<Void> delete(@PathVariable("customer-id") Long customerId) {
    this.service.deleteCustomer(customerId);
    return ResponseEntity.accepted().build();
  }

}
