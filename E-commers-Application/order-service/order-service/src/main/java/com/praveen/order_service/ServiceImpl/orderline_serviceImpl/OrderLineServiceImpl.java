package com.praveen.order_service.ServiceImpl.orderline_serviceImpl;

import com.praveen.order_service.dto.request.orderline_request.OrderLineRequest;
import com.praveen.order_service.dto.response.OrderLineResponse;
import com.praveen.order_service.mapper.OrderLineMapper;
import com.praveen.order_service.model.orderline_model.OrderLine;
import com.praveen.order_service.repository.orderLine_repository.OrderLineRepository;
import com.praveen.order_service.service.orderline_service.OrderLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImpl implements OrderLineService {

    private final OrderLineRepository repository;

    private final OrderLineMapper mapper;

    @Override
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {

        return  repository.findAllByOrderId(orderId).stream().map(mapper::toOrderLineResponse).collect(Collectors.toList());
    }

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = mapper.toOrderLine(request);
        return repository.save(order).getId();
    }

}
