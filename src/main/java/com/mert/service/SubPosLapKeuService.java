package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.SubPosLapKeu;
import com.mert.repository.SubPosLapKeuRepository;

@Service
public class SubPosLapKeuService {
	
	@Autowired
	private SubPosLapKeuRepository subPosLapKeuRepository;
	
	public List<SubPosLapKeu> findAll() {
		return subPosLapKeuRepository.findAll();
	}
	
	public SubPosLapKeu findOne(String id) {
		return subPosLapKeuRepository.findOne(id);
	}
	
	public void save(SubPosLapKeu subPosLapKeu) {
		subPosLapKeuRepository.save(subPosLapKeu);
	}
	
	public void delete(String id) {
		subPosLapKeuRepository.delete(id);
	}
	
	public List<SubPosLapKeu> findByPos(String posId) {
		return subPosLapKeuRepository.findByPos(posId);
	}

}
