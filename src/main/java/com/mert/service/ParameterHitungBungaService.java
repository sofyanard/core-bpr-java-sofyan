package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterHitungBunga;
import com.mert.repository.ParameterHitungBungaRepository;

@Service
public class ParameterHitungBungaService {
	
	@Autowired
	private ParameterHitungBungaRepository parameterHitungBungaRepository;
	
	public List<ParameterHitungBunga> findAll() {
		return parameterHitungBungaRepository.findAll();
	}
	
	public ParameterHitungBunga findOne(String id) {
		return parameterHitungBungaRepository.findOne(id);
	}
	
	public void save(ParameterHitungBunga parameterHitungBunga) {
		parameterHitungBungaRepository.save(parameterHitungBunga);
	}

}
