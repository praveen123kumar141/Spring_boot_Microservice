package com.praveen.order_service.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Builder
public class InventoryResponse {
    private String skuCode;
    private boolean isInStock;
}
