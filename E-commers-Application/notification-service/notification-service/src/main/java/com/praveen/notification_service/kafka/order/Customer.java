package com.praveen.notification_service.kafka.order;

public record Customer(
    String id,
    String firstname,
    String lastname,
    String email
) {

}
