package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.KodeTranUnit;
import com.mert.repository.KodeTranUnitRepository;

@Service
public class KodeTranUnitService {
	
	@Autowired
	private KodeTranUnitRepository kodeTranUnitRepository;
	
	public List<KodeTranUnit> findAll() {
		return kodeTranUnitRepository.findAll();
	}
	
	public List<KodeTranUnit> findByKoTran(String koTran) {
		return kodeTranUnitRepository.findByKoTran(koTran);
	}
	
	public KodeTranUnit findByKoTranAndUnit(String koTran, String unitId) {
		return kodeTranUnitRepository.findByKoTranAndUnit(koTran, unitId);
	}
	
	public KodeTranUnit findOne(Integer id) {
		return kodeTranUnitRepository.findOne(id);
	}
	
	public void save(KodeTranUnit kodeTranUnit) {
		kodeTranUnitRepository.save(kodeTranUnit);
	}
	
	public void delete(Integer id) {
		kodeTranUnitRepository.delete(id);
	}

}
