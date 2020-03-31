package com.hit.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.hit.entity.CdTriggersEntity;

public interface CdTriggersRepository extends CrudRepository<CdTriggersEntity, Integer> {
	public List<CdTriggersEntity> findByTrgStatus(String trgStatus);
	

}
