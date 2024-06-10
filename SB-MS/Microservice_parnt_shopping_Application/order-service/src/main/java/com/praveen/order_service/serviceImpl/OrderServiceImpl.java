package com.praveen.order_service.serviceImpl;

import com.praveen.order_service.config.WebClientConfig;
import com.praveen.order_service.dto.InventoryResponse;
import com.praveen.order_service.dto.OrderLineItemsDto;
import com.praveen.order_service.dto.OrderRequest;
import com.praveen.order_service.model.OrderLineItems;
import com.praveen.order_service.model.Order;
import com.praveen.order_service.repository.OrderRepository;
import com.praveen.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final WebClientConfig webClientConfig;
    @Override
    public String placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> list = orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToDto).toList();
        order.setOrderLineItems(list);
        List<String> skuCodeList = order.getOrderLineItems().stream().map(OrderLineItems::getSkuCode).toList();
        
        //call the inventory service if stock is present place thr order
        InventoryResponse[] skuCodes = webClientConfig.webclient().get()
                .uri("http://localhost:9093/api/inventory/isInStock", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodeList).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        boolean allStockMatch = Arrays.stream(skuCodes).allMatch(InventoryResponse::isInStock);
        if(allStockMatch) {
            orderRepository.save(order);
            return "Order placed successfully";
        }
        else
            throw new IllegalArgumentException("Product Is not In stock please try again later");
    }
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems =new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
