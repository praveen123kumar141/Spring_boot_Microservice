package com.praveen.Inventory_service.service;

import com.praveen.Inventory_service.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    public List<InventoryResponse> isInStock(List<String> skuCode);
}
