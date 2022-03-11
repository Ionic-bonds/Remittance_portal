package com.repository;

import com.model.SelectedField;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApiSelectedFieldRepository extends JpaRepository<SelectedField, Long> {
}
