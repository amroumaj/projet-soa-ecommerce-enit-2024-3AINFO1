package com.soa_ecommerce.inventory.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Inventory {

    private Integer productId;
    private int totalQuantity;
    private int reservedQuantity;
}
