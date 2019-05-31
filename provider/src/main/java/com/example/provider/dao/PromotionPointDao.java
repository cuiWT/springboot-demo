package com.example.provider.dao;

import com.example.provider.model.PromotionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionPointDao extends JpaSpecificationExecutor<PromotionPoint>, JpaRepository<PromotionPoint, Long> {
}
