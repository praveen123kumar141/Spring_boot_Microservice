package com.praveen.product_service.service;

import com.praveen.product_service.dto.ProductRequest;
import com.praveen.product_service.dto.ProductResponse;
import com.praveen.product_service.dto.ProductPurchaseRequest;
import com.praveen.product_service.dto.ProductPurchaseResponse;

import java.util.List;

public interface ProductService {
   public Integer createProduct(ProductRequest request);

    public ProductResponse findById(Integer productId);

    public List<ProductResponse> findAll();

    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request);
}
