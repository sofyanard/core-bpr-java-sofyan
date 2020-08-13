package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.NasabahBasic;
import com.mert.repository.NasabahBasicRepository;

@Service
public class NasabahBasicService {
	
	@Autowired
	private NasabahBasicRepository nasabahBasicRepository;
	
	
	
	public List<NasabahBasic> findAll() {
		return nasabahBasicRepository.findAll();
	}
	
	public List<NasabahBasic> findByKeyword(String keyword) {
		return nasabahBasicRepository.findByKeyword(keyword);
	}
	
	public NasabahBasic findByNonasabah(Long nonasabah) {
		return nasabahBasicRepository.findByNonasabah(nonasabah);
	}

}
