package com.mert.service;

import java.util.List;

import com.mert.model.ParameterOpenClose;
import com.mert.repository.ParameterOpenCloseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParameterOpenCloseService {
	
	@Autowired
	private ParameterOpenCloseRepository parameterOpenCloseRepository;
	
	public List<ParameterOpenClose> findAll() {
		return parameterOpenCloseRepository.findAll();
	}
	
	public ParameterOpenClose findOne(String id) {
		return parameterOpenCloseRepository.findOne(id);
	}
	
	public void save(ParameterOpenClose parameterOpenClose) {
		parameterOpenCloseRepository.save(parameterOpenClose);
	}
	
	public void delete(String id) {
		parameterOpenCloseRepository.delete(id);
	}

}
