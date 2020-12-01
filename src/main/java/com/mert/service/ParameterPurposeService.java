package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterPurpose;
import com.mert.repository.ParameterPurposeRepository;

@Service
public class ParameterPurposeService {
	
	@Autowired
	private ParameterPurposeRepository parameterPurposeRepository;
	
	public List<ParameterPurpose> findAll() {
		return parameterPurposeRepository.findAll();
	}
	
	public ParameterPurpose findOne(String id) {
		return parameterPurposeRepository.findOne(id);
	}
	
	public void save(ParameterPurpose parameterPurpose) {
		parameterPurposeRepository.save(parameterPurpose);
	}

}
