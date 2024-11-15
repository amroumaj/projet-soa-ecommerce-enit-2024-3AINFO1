package com.soa_ecommerce.inventory.service;


import com.soa_ecommerce.inventory.domain.Inventory;
import com.soa_ecommerce.inventory.dto.InventoryRequest;
import com.soa_ecommerce.inventory.exception.InsufficientQuantityException;
import com.soa_ecommerce.inventory.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;
    //ajout d'un produit dans le stock
    public void receiveProduct(UUID productId, Integer quantity){

        inventoryRepository.findById(productId).ifPresentOrElse(
               inventory ->  {
                   inventory.setTotalQuantity(inventory.getTotalQuantity()+quantity);
                   inventoryRepository.save(inventory);
               },
                ()->{
                    Inventory newInventory = Inventory.builder()
                            .productId(productId)
                            .totalQuantity(quantity)
                            .reservedQuantity(0)
                            .build();
                    inventoryRepository.save(newInventory);
                }
        );

    }

    //sortie d'une commande
    public void releaseOrder(List<InventoryRequest> request){
        request.forEach(inventoryRequest -> {
            Inventory inventory = inventoryRepository.findById(inventoryRequest.productId())
                    .orElseThrow(() -> new EntityNotFoundException("Product "+ inventoryRequest.productId() +" not found"));

            if (inventory.getReservedQuantity() <= inventoryRequest.quantity()) {
                throw new InsufficientQuantityException("Cannot release this product "+ inventoryRequest.productId());
            }

            inventory.setReservedQuantity(inventory.getReservedQuantity() - inventoryRequest.quantity());
            inventoryRepository.save(inventory);
        });
    }

    public boolean isExists(UUID productId) {
        return inventoryRepository.existsById(productId);
    }
}
