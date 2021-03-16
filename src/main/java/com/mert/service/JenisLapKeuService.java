package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.JenisLapKeu;
import com.mert.repository.JenisLapKeuRepository;

@Service
public class JenisLapKeuService {
	
	@Autowired
	private JenisLapKeuRepository jenisLapKeuRepository;
	
	public List<JenisLapKeu> findAll() {
		return jenisLapKeuRepository.findAll();
	}
	
	public JenisLapKeu findOne(String id) {
		return jenisLapKeuRepository.findOne(id);
	}
	
	public void save(JenisLapKeu jenisLapKeu) {
		jenisLapKeuRepository.save(jenisLapKeu);
	}
	
	public void delete(String id) {
		jenisLapKeuRepository.delete(id);
	}

}
