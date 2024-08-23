package com.praveen.order_service.kafkaConfig;

import com.praveen.order_service.customer.CustomerResponse;
import com.praveen.order_service.enum_constants.PaymentMethod;
import com.praveen.order_service.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation (
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products

) {
}
