package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.NasabahPengurus;
import com.mert.repository.NasabahPengurusRepository;

@Service
public class NasabahPengurusService {
	
	@Autowired
	private NasabahPengurusRepository nasabahPengurusRepository;
	
	
	
	public List<NasabahPengurus> listByNonasabah(Long nonasabah) {
		return nasabahPengurusRepository.findByNonasabah(nonasabah);
	}
	
	public NasabahPengurus findById(Integer id) {
		return nasabahPengurusRepository.findOne(id);
	}
	
	public void save(NasabahPengurus nasabahPengurus) {
		nasabahPengurusRepository.save(nasabahPengurus);
	}
	
	public void delete(Integer id) {
		nasabahPengurusRepository.delete(id);
	}

}
