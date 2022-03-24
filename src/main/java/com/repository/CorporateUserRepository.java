package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.CorporateUser;

@Repository
public interface CorporateUserRepository extends JpaRepository<CorporateUser, Long> {
  public Optional<CorporateUser> findByUsername(String username);

  public Boolean existsByUsername(String username);

  public Boolean existsByEmail(String email);
}
