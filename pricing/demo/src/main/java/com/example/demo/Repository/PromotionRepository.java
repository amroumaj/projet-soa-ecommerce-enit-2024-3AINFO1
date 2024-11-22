package com.example.demo.Repository;

import com.example.demo.Models.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    List<Promotion> findByProductId(Long productId);
}
