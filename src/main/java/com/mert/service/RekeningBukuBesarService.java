package com.mert.service;

import java.util.List;

import com.mert.model.RekeningBukuBesar;
import com.mert.repository.RekeningBukuBesarRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RekeningBukuBesarService {
	
	@Autowired
	private RekeningBukuBesarRepository rekeningBukuBesarRepository;
	
	public List<RekeningBukuBesar> findAll() {
		return rekeningBukuBesarRepository.findAll();
	}
	
	public RekeningBukuBesar findOne(String id) {
		return rekeningBukuBesarRepository.findOne(id);
	}
	
	public void save(RekeningBukuBesar rekeningBukuBesar) {
		rekeningBukuBesarRepository.save(rekeningBukuBesar);
	}
	
	public void delete(String id) {
		rekeningBukuBesarRepository.delete(id);
	}

}
