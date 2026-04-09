package com.mar1a_ed.e_marketplace.repository;

import com.mar1a_ed.e_marketplace.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
