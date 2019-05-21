package com.finance.homework.repository;

import com.finance.homework.model.ExtentionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExtentionRepository extends JpaRepository<ExtentionEntity, Long> {
}
