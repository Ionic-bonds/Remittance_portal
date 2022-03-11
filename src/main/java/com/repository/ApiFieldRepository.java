package com.repository;

import java.util.List;

import com.model.Api;
import com.model.ApiField;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiFieldRepository extends JpaRepository<ApiField, Long> {
    // Get all Api Fields by api_id
    @Query("SELECT af FROM ApiField af WHERE af.api = :api")
	public List<ApiField> findAllApiFieldByApiId(@Param("api") Api api);
}