package com.praveen.product_service.serviceImpl;

import com.praveen.product_service.dto.ProductRequest;
import com.praveen.product_service.dto.ProductResponse;
import com.praveen.product_service.model.Product;
import com.praveen.product_service.repository.ProductRepository;
import com.praveen.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder().name(productRequest.getName())
                .discription(productRequest.getDiscription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} is saved",product.getId());
    }

    @Override
    public List<ProductResponse> getAllProcucts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> listOfProducts = products.stream().map(this::mapToProductResponse).toList();
        return listOfProducts;
    }
    private ProductResponse mapToProductResponse(Product product){
        ProductResponse productResponse=ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .discription(product.getDiscription())
                .price(product.getPrice()).build();
        return productResponse;
    }
}
