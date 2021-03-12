package com.mert.service;

import java.util.List;

import com.mert.model.KodeEod;
import com.mert.repository.KodeEodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KodeEodService {
	
	@Autowired
	private KodeEodRepository kodeEodRepository;
	
	public List<KodeEod> findAll() {
		return kodeEodRepository.findAll();
	}
	
	public KodeEod findOne(String id) {
		return kodeEodRepository.findOne(id);
	}
	
	public void save(KodeEod kodeEod) {
		kodeEodRepository.save(kodeEod);
	}
	
	public void delete(String id) {
		kodeEodRepository.delete(id);
	}
	
	public List<KodeEod> findAllEom() {
		return kodeEodRepository.findAllEom();
	}
	
	public List<KodeEod> findAllEod() {
		return kodeEodRepository.findAllEod();
	}

}
