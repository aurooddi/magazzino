package com.example.magazzino.repository;

import com.example.magazzino.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

   // public Optional<ProductEntity> searchByDescription(String description);

    Optional<ProductEntity> findByCategoryIgnoreCaseAndDescriptionIgnoreCase(String category, String description);
}
