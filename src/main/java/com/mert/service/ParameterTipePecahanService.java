package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterTipePecahan;
import com.mert.repository.ParameterTipePecahanRepository;

@Service
public class ParameterTipePecahanService {
	
	@Autowired
	private ParameterTipePecahanRepository parameterTipePecahanRepository;
	
	public List<ParameterTipePecahan> findAll() {
		return parameterTipePecahanRepository.findAll();
	}
	
	public ParameterTipePecahan findOne(String id) {
		return parameterTipePecahanRepository.findOne(id);
	}
	
	public void save(ParameterTipePecahan parameterTipePecahan) {
		parameterTipePecahanRepository.save(parameterTipePecahan);
	}

}
