package com.mert.service;

import java.util.List;

import com.mert.model.KodeEodUnit;
import com.mert.repository.KodeEodUnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KodeEodUnitService {
	
	@Autowired
	private KodeEodUnitRepository kodeEodUnitRepository;
	
	public List<KodeEodUnit> findAll() {
		return kodeEodUnitRepository.findAll();
	}
	
	public List<KodeEodUnit> findByKodeEod(String kodeEod) {
		return kodeEodUnitRepository.findByKodeEod(kodeEod);
	}
	
	public KodeEodUnit findOne(Integer id) {
		return kodeEodUnitRepository.findOne(id);
	}
	
	public KodeEodUnit findOne(String kodeEod, String unitId) {
		return kodeEodUnitRepository.findByKodeEodAndUnit(kodeEod, unitId);
	}
	
	public void save(KodeEodUnit kodeEodUnit) {
		kodeEodUnitRepository.save(kodeEodUnit);
	}
	
	public void delete(Integer id) {
		kodeEodUnitRepository.delete(id);
	}

}
