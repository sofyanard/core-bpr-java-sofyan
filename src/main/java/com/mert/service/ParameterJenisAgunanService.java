package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterJenisAgunan;
import com.mert.repository.ParameterJenisAgunanRepository;

@Service
public class ParameterJenisAgunanService {
	
	@Autowired
	private ParameterJenisAgunanRepository parameterJenisAgunanRepository;
	
	public List<ParameterJenisAgunan> findAll() {
		return parameterJenisAgunanRepository.findAll();
	}
	
	public ParameterJenisAgunan findOne(String id) {
		return parameterJenisAgunanRepository.findOne(id);
	}
	
	public void save(ParameterJenisAgunan parameterJenisAgunan) {
		parameterJenisAgunanRepository.save(parameterJenisAgunan);
	}
	
	public void delete(String id) {
		parameterJenisAgunanRepository.delete(id);
	}

}
