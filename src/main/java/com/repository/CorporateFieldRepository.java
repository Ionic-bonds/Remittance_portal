package com.repository;

import java.util.List;

import com.model.CorporateUser;
import com.model.CorporateField;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CorporateFieldRepository extends JpaRepository<CorporateField, Long> {
    // Get all Corporate Fields by corporate_user_id
    @Query("SELECT cf FROM CorporateField cf WHERE cf.corporateUser = :corporateUser")
	public List<CorporateField> findAllCorpFieldByUserId(@Param("corporateUser") CorporateUser corporateUser);
}