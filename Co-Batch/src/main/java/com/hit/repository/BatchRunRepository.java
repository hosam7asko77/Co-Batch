package com.hit.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hit.entity.BatchRunDetailsEntity;

@Repository
public interface BatchRunRepository extends CrudRepository<BatchRunDetailsEntity, Integer> {

}
