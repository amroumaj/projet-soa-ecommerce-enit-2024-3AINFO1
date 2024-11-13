package com.example.demo.services;

import com.example.demo.Models.Promotion;
import com.example.demo.Models.Product;
import com.example.demo.Repository.PromotionRepository;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PromotionService(PromotionRepository promotionRepository, ProductRepository productRepository) {
        this.promotionRepository = promotionRepository;
        this.productRepository = productRepository;
    }

    // Créer une promotion pour un produit
    public Promotion createPromotion(Long productId, Promotion promotion) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new RuntimeException("Product not found");
        }
        promotion.setProduct(product.get());
        return promotionRepository.save(promotion);
    }

    // Obtenir toutes les promotions pour un produit
    public List<Promotion> getPromotionsByProductId(Long productId) {
        return promotionRepository.findByProductId(productId);
    }

    // Supprimer une promotion
    public void deletePromotion(Long productId, Long promotionId) {
        Optional<Promotion> promotion = promotionRepository.findById(promotionId);
        if (promotion.isEmpty() || !promotion.get().getProduct().getId().equals(productId)) {
            throw new RuntimeException("Promotion not found for the product");
        }
        promotionRepository.deleteById(promotionId);
    }
}
