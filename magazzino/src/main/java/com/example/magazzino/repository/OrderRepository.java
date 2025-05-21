package com.example.magazzino.repository;

import com.example.magazzino.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    public Optional<OrderEntity> searchByOrderName(String orderName);

}
