package com.example.provider.dao;

import com.example.provider.model.PromotionPointDef;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionPointDefDao extends JpaSpecificationExecutor<PromotionPointDef>, JpaRepository<PromotionPointDef, Long> {
}
