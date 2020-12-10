package com.mert.service;

import java.util.List;

import com.mert.model.BukuBesar;
import com.mert.repository.BukuBesarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BukuBesarService {
	
	@Autowired
	private BukuBesarRepository bukuBesarRepository;
	
	public List<BukuBesar> findAll() {
		return bukuBesarRepository.findAll();
	}
	
	public BukuBesar findOne(String id) {
		return bukuBesarRepository.findOne(id);
	}
	
	public void save(BukuBesar bukuBesar) {
		bukuBesarRepository.save(bukuBesar);
	}
	
	public void delete(String id) {
		bukuBesarRepository.delete(id);
	}
	
	public BukuBesar findByUnit(String unitId) {
		return bukuBesarRepository.findByUnit(unitId);
	}

}
