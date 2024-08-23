package com.praveen.order_service.ServiceImpl.order_serviceImpl;

import com.praveen.order_service.WEBCLIENT_CONFIG.ConfigWebClient;
import com.praveen.order_service.customer.CustomerClient;
import com.praveen.order_service.customer.CustomerResponse;
import com.praveen.order_service.dto.request.order_request.OrderRequest;
import com.praveen.order_service.dto.request.orderline_request.OrderLineRequest;
import com.praveen.order_service.dto.response.OrderResponse;
import com.praveen.order_service.exception.BusinessException;
import com.praveen.order_service.exception.MyServiceException;
import com.praveen.order_service.exception.ResourceNotFound;
import com.praveen.order_service.kafkaConfig.OrderConfirmation;
import com.praveen.order_service.kafkaConfig.OrderProducer;
import com.praveen.order_service.mapper.OrderMapper;
import com.praveen.order_service.model.order_model.Order;
import com.praveen.order_service.product.ProductClient;
import com.praveen.order_service.product.PurchaseRequest;
import com.praveen.order_service.product.PurchaseResponse;
import com.praveen.order_service.repository.order_repository.OrderRepository;
import com.praveen.order_service.service.order_service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;
import com.praveen.order_service.service.orderline_service.OrderLineService;
import java.net.http.HttpHeaders;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    //private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    /*calling web client*/
    //reference link  https://github.com/codefarm0/microservice-communication-springboot/blob/main/webclient/productclient/src/main/java/com/codefarm/productclient/service/ProductClientService.java
    private final ConfigWebClient webClientconfig;

    @Override
    public Integer createOrder(OrderRequest request) {

        //feign client implementation
        CustomerResponse customerResponse = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));

        //ParameterizedTypeReference<CustomerResponse> =new ParameterizedTypeReference();
        //web client implementation
        ResponseEntity<CustomerResponse> block = webClientconfig.webclient().get()
                .uri("http://localhost:8091/api/v1/customers//{customer-id}", request.customerId())
                .retrieve()
                //.toEntity(ParameterizedTypeReference.forType(CustomerResponse.class))
                .toEntity(CustomerResponse.class)
                .block();

        // POST method Feign client call
        ResponseEntity<List<PurchaseResponse>> purchaseResponses = productClient.purchaseProducts(request.products());

        // POST method call By using webclient
        ResponseEntity<List<PurchaseResponse>> block1 = webClientconfig.webclient().post()
                .uri("http://localhost:8092/api/v1/products/purchase")
                .body(BodyInserters.fromValue(request.products()))
                .retrieve()
                .onRawStatus(
                        status -> status == 400, resonse -> {
                            return Mono.error(new MyServiceException("Bad Request"));
                        })
                .onRawStatus(
                        status -> status == 404, response -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                ).onStatus(
                        HttpStatusCode::is4xxClientError, clientResponse -> {
                            return Mono.error(new ResourceNotFound("No Resource found"));
                        }
                )
                .onStatus(
                        HttpStatusCode::is5xxServerError, clientResponse -> {
                            return Mono.error(new MyServiceException("Server error......"));
                        }
                ).toEntityList(PurchaseResponse.class).block();

        // save the order details in DB
        Order order = repository.save(mapper.toOrder(request));

        for(PurchaseRequest purchaseRequest:request.products()){
            orderLineService.saveOrderLine(new OrderLineRequest(
                    null,
                    order.getId(),
                    purchaseRequest.productId(),
                    purchaseRequest.quantity()
            ));
        }

        orderProducer.sendOrderConfirmation(new OrderConfirmation(
                request.reference(),
                request.amount(),
                request.paymentMethod(),
                customerResponse,
                purchaseResponses.getBody()
        ));

        return order.getId();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromOrder)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findById(Integer orderId) {
        return this.repository.findById(orderId)
                .map(this.mapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", orderId)));

    }
}
