package com.praveen.payment_service.service;

import com.praveen.payment_service.dto.PaymentRequest;

public interface PaymentService {
    public Integer createPayment(PaymentRequest request);
}
