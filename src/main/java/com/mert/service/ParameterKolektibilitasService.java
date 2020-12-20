package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterKolektibilitas;
import com.mert.repository.ParameterKolektibilitasRepository;

@Service
public class ParameterKolektibilitasService {
	
	@Autowired
	private ParameterKolektibilitasRepository parameterKolektibilitasRepository;
	
	public List<ParameterKolektibilitas> findAll() {
		return parameterKolektibilitasRepository.findAll();
	}
	
	public ParameterKolektibilitas findOne(String id) {
		return parameterKolektibilitasRepository.findOne(id);
	}
	
	public void save(ParameterKolektibilitas parameterKolektibilitas) {
		parameterKolektibilitasRepository.save(parameterKolektibilitas);
	}
	
	public void delete(String id) {
		parameterKolektibilitasRepository.delete(id);
	}

}
