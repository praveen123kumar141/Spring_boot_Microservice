package com.praveen.customer_service.dto;

import com.praveen.customer_service.model.Address;

public record CustomerResponse(
    long id,
    String firstname,
    String lastname,
    String email,
    Address address
) {

}
