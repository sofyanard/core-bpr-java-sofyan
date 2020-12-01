package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterGolonganPenjamin;
import com.mert.repository.ParameterGolonganPenjaminRepository;

@Service
public class ParameterGolonganPenjaminService {
	
	@Autowired
	private ParameterGolonganPenjaminRepository parameterGolonganPenjaminRepository;
	
	public List<ParameterGolonganPenjamin> findAll() {
		return parameterGolonganPenjaminRepository.findAll();
	}
	
	public ParameterGolonganPenjamin findOne(String id) {
		return parameterGolonganPenjaminRepository.findOne(id);
	}
	
	public void save(ParameterGolonganPenjamin parameterGolonganPenjamin) {
		parameterGolonganPenjaminRepository.save(parameterGolonganPenjamin);
	}
	
	public void delete(String id) {
		parameterGolonganPenjaminRepository.delete(id);
	}

}
