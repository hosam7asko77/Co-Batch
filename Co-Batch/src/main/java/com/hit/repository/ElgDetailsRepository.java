package com.hit.repository;

import org.springframework.data.repository.CrudRepository;

import com.hit.entity.ElgDetailsEntity;

public interface ElgDetailsRepository extends CrudRepository<ElgDetailsEntity, Integer> {

	public ElgDetailsEntity findByCaseNumber(Integer caseNum);

}
