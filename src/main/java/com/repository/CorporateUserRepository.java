package com.repository;

import com.model.CorporateUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface CorporateUserRepository extends JpaRepository<CorporateUser, Long> {
    Optional<CorporateUser> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}