package com.praveen.customer_service.service;


import com.praveen.customer_service.dto.CustomerRequest;
import com.praveen.customer_service.dto.CustomerResponse;

import java.util.List;

public interface CustomerService {

    public CustomerResponse createCustomer(CustomerRequest request);

   public String updateCustomer(CustomerRequest request);

    public List<CustomerResponse> findAllCustomers();

    public Boolean existsById(long customerId);

    public CustomerResponse findById(long customerId);

    public void deleteCustomer(long customerId);
}
