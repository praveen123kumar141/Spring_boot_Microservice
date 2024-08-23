package com.praveen.notification_service.kafka.payment;

import com.praveen.notification_service.kafka.order.OrderConfirmation;

import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.praveen.notification_service.notification.NotificationType.ORDER_CONFIRMATION;
import static com.praveen.notification_service.notification.NotificationType.PAYMENT_CONFIRMATION;
import static java.lang.String.format;

@Service
@Slf4j
//@RequiredArgsConstructor
public class NotificationsConsumer {

    //private final NotificationRepository repository;

    @KafkaListener(topics = "payment-topic")
    public void consumePaymentSuccessNotifications(PaymentConfirmation paymentConfirmation) throws MessagingException {
        log.info(format("Consuming the message from payment-topic Topic:: %s", paymentConfirmation));
    }

    @KafkaListener(topics = "order-topic")
    public void consumeOrderConfirmationNotifications(OrderConfirmation orderConfirmation) throws MessagingException {
        log.info(format("Consuming the message from order-topic Topic:: %s", orderConfirmation));
    }
}
