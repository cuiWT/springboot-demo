package com.example.provider.dao;

import com.example.provider.model.Dinner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DinnerDao extends JpaSpecificationExecutor<Dinner>, JpaRepository<Dinner, Long> {

}
