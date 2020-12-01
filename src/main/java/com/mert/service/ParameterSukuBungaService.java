package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterSukuBunga;
import com.mert.repository.ParameterSukuBungaRepository;

@Service
public class ParameterSukuBungaService {
	
	@Autowired
	private ParameterSukuBungaRepository parameterSukuBungaRepository;
	
	public List<ParameterSukuBunga> findAll() {
		return parameterSukuBungaRepository.findAll();
	}
	
	public ParameterSukuBunga findOne(String id) {
		return parameterSukuBungaRepository.findOne(id);
	}
	
	public void save(ParameterSukuBunga parameterSukuBunga) {
		parameterSukuBungaRepository.save(parameterSukuBunga);
	}

}
