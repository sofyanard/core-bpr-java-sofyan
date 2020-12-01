package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterProductType;
import com.mert.repository.ParameterProductTypeRepository;

@Service
public class ParameterProductTypeService {
	
	@Autowired
	private ParameterProductTypeRepository parameterProductTypeRepository;
	
	public List<ParameterProductType> findAll() {
		return parameterProductTypeRepository.findAll();
	}
	
	public ParameterProductType findOne(String id) {
		return parameterProductTypeRepository.findOne(id);
	}
	
	public void save(ParameterProductType parameterProductType) {
		parameterProductTypeRepository.save(parameterProductType);
	}

}
