package com.praveen.payment_service.ServiceImpl;

import com.praveen.payment_service.dto.PaymentRequest;
import com.praveen.payment_service.kafkaconfig.notification.NotificationProducer;
import com.praveen.payment_service.kafkaconfig.notification.PaymentNotificationRequest;
import com.praveen.payment_service.mapper.PaymentMapper;
import com.praveen.payment_service.repository.PaymentRepository;
import com.praveen.payment_service.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;
    private final PaymentMapper mapper;
    private final NotificationProducer notificationProducer;

    @Override
    public Integer createPayment(PaymentRequest request) {

        var payment = this.repository.save(this.mapper.toPayment(request));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}
