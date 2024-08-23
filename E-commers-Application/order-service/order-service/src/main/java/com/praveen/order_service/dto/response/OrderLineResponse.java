package com.praveen.order_service.dto.response;

public record OrderLineResponse(
        Integer id,
        double quantity
) { }
