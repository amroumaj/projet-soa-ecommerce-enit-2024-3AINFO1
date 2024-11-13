package com.example.demo.controllers;

import com.example.demo.Models.Product;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Ajouter ou mettre à jour un produit (avec son prix)
    @PostMapping
    public ResponseEntity<Product> createOrUpdateProduct(@RequestBody Product product) {
        Product savedProduct = productService.saveOrUpdateProduct(product);
        return ResponseEntity.ok(savedProduct);
    }

    // Obtenir tous les produits
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Obtenir un produit spécifique
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // Mettre à jour le prix d'un produit
    @PutMapping("/{id}/price")
    public ResponseEntity<Product> updateProductPrice(@PathVariable Long id, @RequestBody Double price) {
        Product updatedProduct = productService.updateProductPrice(id, price);
        return ResponseEntity.ok(updatedProduct);
    }
}
