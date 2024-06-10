package com.praveen.product_service.service;

import com.praveen.product_service.dto.ProductRequest;
import com.praveen.product_service.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    public void createProduct(ProductRequest productRequest);

    public List<ProductResponse> getAllProcucts();
}
