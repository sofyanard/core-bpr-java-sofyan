package com.mert.service;

import java.util.List;

import com.mert.model.KodeTran;
import com.mert.repository.KodeTranRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KodeTranService {
	
	@Autowired
	private KodeTranRepository kodeTranRepository;
	
	public List<KodeTran> findAll() {
		return kodeTranRepository.findAll();
	}
	
	public KodeTran findOne(String id) {
		return kodeTranRepository.findOne(id);
	}
	
	public void save(KodeTran kodeTran) {
		kodeTranRepository.save(kodeTran);
	}
	
	public void delete(String id) {
		kodeTranRepository.delete(id);
	}
	
	
	
	public List<KodeTran> ListEodPosting() {
		return kodeTranRepository.ListEodPosting();
	}

}
