package com.soa_ecommerce.inventory.service;


import com.soa_ecommerce.inventory.domain.Inventory;
import com.soa_ecommerce.inventory.repository.InventoryRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class inventoryService {

    private final InventoryRepository inventoryRepository;
    //ajout d'un nouveau produit dans le stock
    public void receiveProduct(int productId,int quantity){
        Inventory inventory =inventoryRepository.getInventory(productId);
        if(inventory!= null){
            inventory.setTotalQuantity(inventory.getTotalQuantity()+quantity);
            inventoryRepository.updateInventory(productId,inventory);
        }else{
            inventoryRepository.addInventory(Inventory.builder()
                    .productId(productId)
                    .totalQuantity(quantity)
                    .reservedQuantity(0)
                    .build());
        }
    }

    //sortie d'une commande
    public void releaseProduct(int productId,int quantity){
        Inventory inventory =inventoryRepository.getInventory(productId);
        inventory.setReservedQuantity(inventory.getReservedQuantity() - quantity);
        inventoryRepository.updateInventory(productId, inventory);
    }

}
