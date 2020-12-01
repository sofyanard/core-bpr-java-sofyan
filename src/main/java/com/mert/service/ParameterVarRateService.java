package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterVarRate;
import com.mert.repository.ParameterVarRateRepository;

@Service
public class ParameterVarRateService {
	
	@Autowired
	private ParameterVarRateRepository parameterVarRateRepository;
	
	public List<ParameterVarRate> findAll() {
		return parameterVarRateRepository.findAll();
	}
	
	public ParameterVarRate findOne(String id) {
		return parameterVarRateRepository.findOne(id);
	}
	
	public void save(ParameterVarRate parameterVarRate) {
		parameterVarRateRepository.save(parameterVarRate);
	}

}
