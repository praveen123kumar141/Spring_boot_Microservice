package com.praveen.customer_service.ServiceImpl;



import com.praveen.customer_service.dto.CustomerMapper;
import com.praveen.customer_service.dto.CustomerRequest;
import com.praveen.customer_service.dto.CustomerResponse;
import com.praveen.customer_service.exception.CustomerNotFoundException;
import com.praveen.customer_service.model.Customer;
import com.praveen.customer_service.repository.CustomerRepository;
import com.praveen.customer_service.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

  private final CustomerRepository repository;
  private final CustomerMapper mapper;

  public CustomerResponse createCustomer(CustomerRequest request) {
    var customer = this.repository.save(mapper.toCustomer(request));
    return mapper .fromCustomer(customer);
  }

  public String updateCustomer(CustomerRequest request) {
    Customer customer = this.repository.findById(request.id()).get();
    if(customer.getId()>0){
      mergeCustomer(customer, request);
      this.repository.save(customer);
      return "Customer details updated successfully";
    }else{
      throw new CustomerNotFoundException(
              String.format("Cannot update customer:: No customer found with the provided ID: %s", request.id())
      );
    }
  }

  private void mergeCustomer(Customer customer, CustomerRequest request) {
    if (StringUtils.isNotBlank(request.firstname())) {
      customer.setFirstname(request.firstname());
    }
    if (StringUtils.isNotBlank(request.email())) {
      customer.setEmail(request.email());
    }
    if (request.address() != null) {
      customer.setAddress(request.address());
    }
  }

  public List<CustomerResponse> findAllCustomers() {
    return  this.repository.findAll()
        .stream()
        .map(this.mapper::fromCustomer)
        .collect(Collectors.toList());
  }

  public CustomerResponse findById(long id) {
    return this.repository.findById(id)
        .map(mapper::fromCustomer)
        .orElseThrow(() -> new CustomerNotFoundException(String.format("No customer found with the provided ID: %s", id)));
  }

  public Boolean existsById(long id) {
    return this.repository.findById(id)
        .isPresent();
  }

  public void deleteCustomer(long id) {
    this.repository.deleteById(id);
  }
}
