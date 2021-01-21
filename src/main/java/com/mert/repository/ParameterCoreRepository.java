package com.mert.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mert.model.ParameterCore;

public interface ParameterCoreRepository extends JpaRepository<ParameterCore, Integer> {
	
	ParameterCore findByParamkey(String paramkey);

}
