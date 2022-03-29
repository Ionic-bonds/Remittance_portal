package com.repository;

import java.util.List;

import com.model.CorporateUser;
import com.model.Transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Get all Transactions by corporate_user_id
    @Query("SELECT t FROM Transaction t WHERE t.corporateUser = :corporateUser")
    public List<Transaction> findAllTransactByCorpUserId(@Param("corporateUser") CorporateUser corporateUser);
}