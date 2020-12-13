package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterKodeBiaya;
import com.mert.repository.ParameterKodeBiayaRepository;

@Service
public class ParameterKodeBiayaService {
	@Autowired
	private ParameterKodeBiayaRepository parameterkodeBiayaRepository;
	
	public ParameterKodeBiayaService(ParameterKodeBiayaRepository parameterkodeBiayaRepository) {
		// TODO Auto-generated constructor stub
		this.parameterkodeBiayaRepository = parameterkodeBiayaRepository;
	}
	
	public List<ParameterKodeBiaya> findAll(){
		List<ParameterKodeBiaya> parameterkodeBiaya = new ArrayList<>();
		parameterkodeBiaya = parameterkodeBiayaRepository.findAll();
		return parameterkodeBiaya;
	}
	
	public List<ParameterKodeBiaya> ListAllParameterKodeBiaya(){
		return parameterkodeBiayaRepository.findAll();
	}
	
	public ParameterKodeBiaya findById(String code) {
		return parameterkodeBiayaRepository.findOne(code);
	}
	
	public ParameterKodeBiaya FindParameterKodeBiaya(String code) {
		return parameterkodeBiayaRepository.findOne(code);
	}
	
	public void save(ParameterKodeBiaya parameterkodeBiaya){
		parameterkodeBiayaRepository.save(parameterkodeBiaya);
	}
	
	public void delete(String code){
		parameterkodeBiayaRepository.delete(code);

	}
}
