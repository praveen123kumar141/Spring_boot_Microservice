package com.praveen.Inventory_service.serviceImpl;

import com.praveen.Inventory_service.dto.InventoryResponse;
import com.praveen.Inventory_service.repository.InventoryRepository;
import com.praveen.Inventory_service.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        return inventoryRepository.findBySkucodeIn(skuCodes).stream().map(
                inventory -> InventoryResponse.builder().skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0).build()
        ).toList();
    }
}
