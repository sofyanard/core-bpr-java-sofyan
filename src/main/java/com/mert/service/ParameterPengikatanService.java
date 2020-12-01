package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterPengikatan;
import com.mert.repository.ParameterPengikatanRepository;

@Service
public class ParameterPengikatanService {
	
	@Autowired
	private ParameterPengikatanRepository parameterPengikatanRepository;
	
	public List<ParameterPengikatan> findAll() {
		return parameterPengikatanRepository.findAll();
	}
	
	public ParameterPengikatan findOne(String id) {
		return parameterPengikatanRepository.findOne(id);
	}
	
	public void save(ParameterPengikatan parameterPengikatan) {
		parameterPengikatanRepository.save(parameterPengikatan);
	}
	
	public void delete(String id) {
		parameterPengikatanRepository.delete(id);
	}

}
