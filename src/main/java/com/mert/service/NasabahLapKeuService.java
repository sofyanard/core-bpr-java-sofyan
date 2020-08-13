package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.NasabahLapKeu;
import com.mert.repository.NasabahLapKeuRepository;

@Service
public class NasabahLapKeuService {
	
	@Autowired
	private NasabahLapKeuRepository nasabahLapKeuRepository;
	
	
	
	public List<NasabahLapKeu> listByNonasabah(Long nonasabah) {
		return nasabahLapKeuRepository.findByNonasabah(nonasabah);
	}
	
	public NasabahLapKeu findById(Integer id) {
		return nasabahLapKeuRepository.findOne(id);
	}
	
	public void save(NasabahLapKeu nasabahLapKeu) {
		nasabahLapKeuRepository.save(nasabahLapKeu);
	}
	
	public void delete(Integer id) {
		nasabahLapKeuRepository.delete(id);
	}

}
