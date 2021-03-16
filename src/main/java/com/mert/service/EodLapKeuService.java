package com.mert.service;

import java.util.List;

import com.mert.model.EodLapKeu;
import com.mert.repository.EodLapKeuRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EodLapKeuService {
	
	@Autowired
	private EodLapKeuRepository eodLapKeuRepository;
	
	public List<EodLapKeu> findAll() {
		return eodLapKeuRepository.findAll();
	}
	
	public EodLapKeu findOne(String id) {
		return eodLapKeuRepository.findOne(id);
	}
	
	public void save(EodLapKeu eodLapKeu) {
		eodLapKeuRepository.save(eodLapKeu);
	}
	
	public void delete(String id) {
		eodLapKeuRepository.delete(id);
	}
	
	public List<EodLapKeu> findAllEom() {
		return eodLapKeuRepository.findAllEom();
	}
	
	public List<EodLapKeu> findAllEod() {
		return eodLapKeuRepository.findAllEod();
	}

}
