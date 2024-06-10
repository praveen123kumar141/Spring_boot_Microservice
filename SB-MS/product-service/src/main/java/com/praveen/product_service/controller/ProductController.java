package com.praveen.product_service.controller;

import com.praveen.product_service.dto.ProductRequest;
import com.praveen.product_service.dto.ProductResponse;
import com.praveen.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping(value="/create-product")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
            productService.createProduct(productRequest);
    }
    @GetMapping(value="/all-products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        List<ProductResponse> allProcucts = productService.getAllProcucts();
        return allProcucts;
    }
}
