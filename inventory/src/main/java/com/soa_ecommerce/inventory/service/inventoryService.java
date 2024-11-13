package com.soa_ecommerce.inventory.service;


import com.soa_ecommerce.inventory.domain.Inventory;
import com.soa_ecommerce.inventory.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class inventoryService {

    private final InventoryRepository inventoryRepository;
    //ajout d'un produit dans le stock
    public void receiveProduct(Integer productId,Integer quantity){

        inventoryRepository.findById(productId).ifPresentOrElse(
               inventory ->  {
                   inventory.setTotalQuantity(inventory.getTotalQuantity()+quantity);
                   inventoryRepository.save(inventory);
               },
                ()->{
                    inventoryRepository.save(Inventory.builder()
                            .productId(productId)
                            .totalQuantity(quantity)
                            .reservedQuantity(0)
                            .build());
                }
        );

    }

    //sortie d'une commande
    public void releaseProduct(Integer productId,Integer quantity){
        Inventory inventory =inventoryRepository.findById(productId).orElseThrow(()-> new EntityNotFoundException("Inventory not found"));
        inventory.setReservedQuantity(inventory.getReservedQuantity() - quantity);
        inventoryRepository.save(inventory);
    }

}
