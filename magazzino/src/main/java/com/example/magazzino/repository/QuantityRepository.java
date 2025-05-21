package com.example.magazzino.repository;

import com.example.magazzino.model.entity.QuantityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantityRepository extends JpaRepository<QuantityEntity, Long> {

}
