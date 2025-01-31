package com.example.magazzino.repository;

import com.example.magazzino.model.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    public Optional<CustomerEntity> searchByEmail(String email);

}
