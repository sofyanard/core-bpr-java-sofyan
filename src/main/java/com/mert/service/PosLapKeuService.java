package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.PosLapKeu;
import com.mert.repository.PosLapKeuRepository;

@Service
public class PosLapKeuService {
	
	@Autowired
	private PosLapKeuRepository posLapKeuRepository;
	
	public List<PosLapKeu> findAll() {
		return posLapKeuRepository.findAll();
	}
	
	public PosLapKeu findOne(String id) {
		return posLapKeuRepository.findOne(id);
	}
	
	public void save(PosLapKeu posLapKeu) {
		posLapKeuRepository.save(posLapKeu);
	}
	
	public void delete(String id) {
		posLapKeuRepository.delete(id);
	}
	
	public List<PosLapKeu> findByPosisi(String posisiId) {
		return posLapKeuRepository.findByPosisi(posisiId);
	}

}
