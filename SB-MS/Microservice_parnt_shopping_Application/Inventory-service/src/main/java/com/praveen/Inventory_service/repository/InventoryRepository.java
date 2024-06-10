package com.praveen.Inventory_service.repository;

import com.praveen.Inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    @Query(nativeQuery = true,value = "select * from t_Inventory where sku_code=?")
    Optional<Inventory> findBySkuCode(String skuCode);

    @Query(nativeQuery = true,value = "select * from t_Inventory where sku_code in(:skuCodes)")
    public List<Inventory> findBySkucodeIn(List<String> skuCodes);
}
