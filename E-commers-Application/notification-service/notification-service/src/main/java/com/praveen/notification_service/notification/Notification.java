package com.praveen.notification_service.notification;

import com.praveen.notification_service.kafka.order.OrderConfirmation;
import com.praveen.notification_service.kafka.payment.PaymentConfirmation;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

public class Notification {

     private Long id;
    private NotificationType type;
    private LocalDateTime notificationDate;
    private OrderConfirmation orderConfirmation;
    private PaymentConfirmation paymentConfirmation;
}
