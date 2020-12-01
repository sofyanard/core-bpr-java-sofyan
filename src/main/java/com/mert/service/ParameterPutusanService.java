package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterPutusan;
import com.mert.repository.ParameterPutusanRepository;

@Service
public class ParameterPutusanService {
	
	@Autowired
	private ParameterPutusanRepository parameterPutusanRepository;
	
	public List<ParameterPutusan> findAll() {
		return parameterPutusanRepository.findAll();
	}
	
	public ParameterPutusan findOne(String id) {
		return parameterPutusanRepository.findOne(id);
	}
	
	public void save(ParameterPutusan parameterPutusan) {
		parameterPutusanRepository.save(parameterPutusan);
	}

}
