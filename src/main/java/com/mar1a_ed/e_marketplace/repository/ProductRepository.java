package com.mar1a_ed.e_marketplace.repository;

import com.mar1a_ed.e_marketplace.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByNameContainingIgnoreCase(String name);

    Product findByCode(String code);
}
