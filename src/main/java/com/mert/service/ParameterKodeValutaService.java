package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterKodeValuta;
import com.mert.repository.ParameterKodeValutaRepository;

@Service
public class ParameterKodeValutaService {
	
	@Autowired
	private ParameterKodeValutaRepository parameterKodeValutaRepository;
	
	public List<ParameterKodeValuta> findAll() {
		return parameterKodeValutaRepository.findAll();
	}
	
	public ParameterKodeValuta findOne(String id) {
		return parameterKodeValutaRepository.findOne(id);
	}
	
	public void save(ParameterKodeValuta parameterKodeValuta) {
		parameterKodeValutaRepository.save(parameterKodeValuta);
	}

}
