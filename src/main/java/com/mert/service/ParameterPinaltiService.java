package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterPinalti;
import com.mert.repository.ParameterPinaltiRepository;

@Service
public class ParameterPinaltiService {
	
	@Autowired
	private ParameterPinaltiRepository parameterPinaltiRepository;
	
	public List<ParameterPinalti> findAll() {
		return parameterPinaltiRepository.findAll();
	}
	
	public ParameterPinalti findOne(String id) {
		return parameterPinaltiRepository.findOne(id);
	}
	
	public void save(ParameterPinalti parameterPinalti) {
		parameterPinaltiRepository.save(parameterPinalti);
	}

}
