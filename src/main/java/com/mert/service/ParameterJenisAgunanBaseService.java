package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterJenisAgunanBase;
import com.mert.repository.ParameterJenisAgunanBaseRepository;

@Service
public class ParameterJenisAgunanBaseService {
	
	@Autowired
	private ParameterJenisAgunanBaseRepository parameterJenisAgunanBaseRepository;
	
	public List<ParameterJenisAgunanBase> findAll() {
		return parameterJenisAgunanBaseRepository.findAll();
	}
	
	public ParameterJenisAgunanBase findOne(String id) {
		return parameterJenisAgunanBaseRepository.findOne(id);
	}
	
	public void save(ParameterJenisAgunanBase parameterJenisAgunanBase) {
		parameterJenisAgunanBaseRepository.save(parameterJenisAgunanBase);
	}
	
	public void delete(String id) {
		parameterJenisAgunanBaseRepository.delete(id);
	}

}
