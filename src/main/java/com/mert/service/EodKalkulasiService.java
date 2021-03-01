package com.mert.service;

import java.util.List;

import com.mert.model.EodKalkulasi;
import com.mert.repository.EodKalkulasiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EodKalkulasiService {
	
	@Autowired
	private EodKalkulasiRepository eodKalkulasiRepository;
	
	public List<EodKalkulasi> findAll() {
		return eodKalkulasiRepository.findAll();
	}
	
	public EodKalkulasi findOne(Long id) {
		return eodKalkulasiRepository.findOne(id);
	}
	
	public void save(EodKalkulasi eodKalkulasi) {
		eodKalkulasiRepository.save(eodKalkulasi);
	}
	
	public void delete(Long id) {
		eodKalkulasiRepository.delete(id);
	}

}
