package com.example.demo.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findById(long id);

    List<Product> findAllByFactory(String factory);

    long countByFactory(String factory);

}
