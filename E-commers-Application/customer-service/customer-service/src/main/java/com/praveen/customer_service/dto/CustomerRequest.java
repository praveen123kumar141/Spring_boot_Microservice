package com.praveen.customer_service.dto;


import com.praveen.customer_service.model.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
    long id,
    @NotNull(message = "Customer firstname is required")
    String firstname,
    @NotNull(message = "Customer firstname is required")
    String lastname,
    @NotNull(message = "Customer Email is required")
    @Email(message = "Customer Email is not a valid email address")
    String email,
    Address address
) {

}
