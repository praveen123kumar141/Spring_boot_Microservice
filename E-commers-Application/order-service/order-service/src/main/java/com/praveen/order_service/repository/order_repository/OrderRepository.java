package com.praveen.order_service.repository.order_repository;

import com.praveen.order_service.model.order_model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
