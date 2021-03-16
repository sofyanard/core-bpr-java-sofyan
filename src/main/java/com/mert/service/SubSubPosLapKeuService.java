package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.SubSubPosLapKeu;
import com.mert.repository.SubSubPosLapKeuRepository;

@Service
public class SubSubPosLapKeuService {
	
	@Autowired
	private SubSubPosLapKeuRepository subSubPosLapKeuRepository;
	
	public List<SubSubPosLapKeu> findAll() {
		return subSubPosLapKeuRepository.findAll();
	}
	
	public SubSubPosLapKeu findOne(String id) {
		return subSubPosLapKeuRepository.findOne(id);
	}
	
	public void save(SubSubPosLapKeu subSubPosLapKeu) {
		subSubPosLapKeuRepository.save(subSubPosLapKeu);
	}
	
	public void delete(String id) {
		subSubPosLapKeuRepository.delete(id);
	}
	
	public List<SubSubPosLapKeu> findBySubPos(String subPosId) {
		return subSubPosLapKeuRepository.findBySubPos(subPosId);
	}

}
