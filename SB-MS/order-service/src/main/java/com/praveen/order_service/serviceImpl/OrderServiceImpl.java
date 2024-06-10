package com.praveen.order_service.serviceImpl;

import com.praveen.order_service.dto.OrderLineItemsDto;
import com.praveen.order_service.dto.OrderRequest;
import com.praveen.order_service.model.OrderLineItems;
import com.praveen.order_service.model.Order;
import com.praveen.order_service.repository.OrderRepository;
import com.praveen.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> list = orderRequest.getOrderLineItemsDtoList().stream()
                .map(this::mapToDto).toList();
        order.setOrderLineItems(list);
        orderRepository.save(order);
    }
    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems =new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
