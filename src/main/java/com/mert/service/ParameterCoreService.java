package com.mert.service;

import java.util.List;

import com.mert.model.ParameterCore;
import com.mert.repository.ParameterCoreRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterCoreService {
	
	@Autowired
	private ParameterCoreRepository parameterCoreRepository;
	
	public List<ParameterCore> findAll() {
		return parameterCoreRepository.findAll();
	}
	
	public ParameterCore findOne(Integer id) {
		return parameterCoreRepository.findOne(id);
	}
	
	public void save(ParameterCore parameterCore) {
		parameterCoreRepository.save(parameterCore);
	}
	
	public void delete(Integer id) {
		parameterCoreRepository.delete(id);
	}
	
	
	
	public ParameterCore findByParamkey(String paramkey) {
		return parameterCoreRepository.findByParamkey(paramkey);
	}
	
}
