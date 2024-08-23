package com.praveen.order_service.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@FeignClient(
        name="product-service",
        url = "http://localhost:8092/api/v1/products"
)
public interface ProductClient {

    @PostMapping(value = "/purchase")
    public ResponseEntity<List<PurchaseResponse>> purchaseProducts(List<PurchaseRequest> requestBody);
}
