package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.DokumenAgunan;
import com.mert.repository.DokumenAgunanRepository;

@Service
public class DokumenAgunanService {
	
	@Autowired
	private DokumenAgunanRepository dokumenAgunanRepository;
	
	public List<DokumenAgunan> findAll() {
		return dokumenAgunanRepository.findAll();
	}
	
	public List<DokumenAgunan> FindByNoAgunan(String noagunan) {
		return dokumenAgunanRepository.FindByNoAgunan(noagunan);
	}
	
	public DokumenAgunan findOne(Integer id) {
		return dokumenAgunanRepository.findOne(id);
	}
	
	public void save(DokumenAgunan dokumenAgunan) {
		dokumenAgunanRepository.save(dokumenAgunan);
	}
	
	public void delete(Integer id) {
		dokumenAgunanRepository.delete(id);
	}

}
