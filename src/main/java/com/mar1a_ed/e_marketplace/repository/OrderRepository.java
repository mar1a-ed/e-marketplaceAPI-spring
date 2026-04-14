package com.mar1a_ed.e_marketplace.repository;

import com.mar1a_ed.e_marketplace.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
