package com.mert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.NasabahCatatan;
import com.mert.repository.NasabahCatatanRepository;

@Service
public class NasabahCatatanService {
	
	@Autowired
	private NasabahCatatanRepository nasabahCatatanRepository;
	
	
	
	public NasabahCatatan findById(Long nonasabah) {
		return nasabahCatatanRepository.findOne(nonasabah);
	}
	
	public void save(NasabahCatatan nasabahCatatan) {
		nasabahCatatanRepository.save(nasabahCatatan);
	}
	
	public void delete(Long nonasabah) {
		nasabahCatatanRepository.delete(nonasabah);
	}

}
