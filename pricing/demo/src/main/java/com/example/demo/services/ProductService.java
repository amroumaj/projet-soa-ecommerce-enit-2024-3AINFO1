package com.example.demo.services;

import com.example.demo.Models.Product;
import com.example.demo.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Sauvegarder ou mettre à jour un produit
    public Product saveOrUpdateProduct(Product product) {
        return productRepository.save(product);
    }

    // Obtenir tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Obtenir un produit par son ID
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // Mettre à jour le prix d'un produit
    public Product updateProductPrice(Long id, Double price) {
        Product product = getProductById(id);
        product.setPrice(price);
        return productRepository.save(product);
    }
}
