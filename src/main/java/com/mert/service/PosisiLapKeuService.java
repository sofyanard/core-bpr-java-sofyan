package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.PosisiLapKeu;
import com.mert.repository.PosisiLapKeuRepository;

@Service
public class PosisiLapKeuService {
	
	@Autowired
	private PosisiLapKeuRepository posisiLapKeuRepository;
	
	public List<PosisiLapKeu> findAll() {
		return posisiLapKeuRepository.findAll();
	}
	
	public PosisiLapKeu findOne(String id) {
		return posisiLapKeuRepository.findOne(id);
	}
	
	public void save(PosisiLapKeu posisiLapKeu) {
		posisiLapKeuRepository.save(posisiLapKeu);
	}
	
	public void delete(String id) {
		posisiLapKeuRepository.delete(id);
	}
	
	public List<PosisiLapKeu> findByJenis(String jenisId) {
		return posisiLapKeuRepository.findByJenis(jenisId);
	}

}
