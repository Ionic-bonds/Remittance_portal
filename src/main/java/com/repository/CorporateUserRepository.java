package com.repository;

import com.model.CorporateUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateUserRepository extends JpaRepository<CorporateUser, Long> {
}