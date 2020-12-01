package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterStatusAgunan;
import com.mert.repository.ParameterStatusAgunanRepository;

@Service
public class ParameterStatusAgunanService {
	
	@Autowired
	private ParameterStatusAgunanRepository parameterStatusAgunanRepository;
	
	public List<ParameterStatusAgunan> findAll() {
		return parameterStatusAgunanRepository.findAll();
	}
	
	public ParameterStatusAgunan findOne(String id) {
		return parameterStatusAgunanRepository.findOne(id);
	}
	
	public void save(ParameterStatusAgunan parameterStatusAgunan) {
		parameterStatusAgunanRepository.save(parameterStatusAgunan);
	}
	
	public void delete(String id) {
		parameterStatusAgunanRepository.delete(id);
	}

}
