package com.mar1a_ed.e_marketplace.repository;

import com.mar1a_ed.e_marketplace.model.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
