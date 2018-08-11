package com.example.provider.dao;

import com.example.provider.model.Dinner;
import org.springframework.data.repository.CrudRepository;

public interface DinnerDao extends CrudRepository<Dinner, Long> {

}
