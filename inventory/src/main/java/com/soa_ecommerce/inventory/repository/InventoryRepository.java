package com.soa_ecommerce.inventory.repository;

import com.soa_ecommerce.inventory.domain.Inventory;
import lombok.Data;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Data
@Repository
public class InventoryRepository {
    public static Map<Integer, Inventory> db_inventory =new HashMap<>();

    public void addInventory(Inventory inventory){
        db_inventory.put(inventory.getProductId(),inventory);
    }

    public Inventory getInventory(int productId){
        return db_inventory.get(productId);
    }

    public void updateInventory(int productId, Inventory inventory) {
        db_inventory.put(productId, inventory);
    }


    public Integer deleteInventory(int productId){
        db_inventory.remove(productId);
        return productId;
    }
}
