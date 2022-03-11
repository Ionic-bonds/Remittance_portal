package com.repository;

import java.util.List;

import com.model.ApiField;
import com.model.SelectedField;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SelectedFieldRepository extends JpaRepository<SelectedField, Long> {
    // Get all Api Fields by api_id
    @Query("SELECT sf FROM SelectedField sf WHERE sf.apiField = :apiField")
    public List<SelectedField> findAllSelectedByApiFieldId(@Param("apiField") ApiField apiField);
}