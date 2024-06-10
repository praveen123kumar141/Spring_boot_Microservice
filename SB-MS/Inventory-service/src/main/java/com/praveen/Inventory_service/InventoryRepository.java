package com.praveen.Inventory_service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    @Query(nativeQuery = true,value = "select * from t_Inventory where sku_code=?")
    Optional<Inventory> findBySkuCode(String skuCode);
}
