package com.example.demo.controllers;

import com.example.demo.Models.Promotion;
import com.example.demo.services.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @Autowired
    public PromotionController(PromotionService promotionService) {
        this.promotionService = promotionService;
    }

    // Ajouter une promotion pour un produit
    @PostMapping
    public ResponseEntity<Promotion> createPromotion(@PathVariable Long productId, @RequestBody Promotion promotion) {
        Promotion savedPromotion = promotionService.createPromotion(productId, promotion);
        return ResponseEntity.ok(savedPromotion);
    }

    // Obtenir toutes les promotions pour un produit
    @GetMapping
    public List<Promotion> getPromotionsByProductId(@PathVariable Long productId) {
        return promotionService.getPromotionsByProductId(productId);
    }

    // Supprimer une promotion pour un produit
    @DeleteMapping("/{promotionId}")
    public ResponseEntity<Void> deletePromotion(@PathVariable Long productId, @PathVariable Long promotionId) {
        promotionService.deletePromotion(productId, promotionId);
        return ResponseEntity.noContent().build();
    }
}
