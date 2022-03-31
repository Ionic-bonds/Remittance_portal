package com.repository;

import java.util.List;

import com.model.CorporateUser;
import com.model.UserApiRequest;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserApiRequestRepository extends JpaRepository<UserApiRequest, Long> {
    // Get all Transactions by corporate_user_id
    @Query("SELECT uar FROM UserApiRequest uar WHERE uar.corporateUser = :corporateUser")
    public List<UserApiRequest> findAllApiRequestByCorpUserId(@Param("corporateUser") CorporateUser corporateUser);
}