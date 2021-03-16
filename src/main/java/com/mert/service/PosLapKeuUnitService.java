package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.PosLapKeuUnit;
import com.mert.repository.PosLapKeuUnitRepository;

@Service
public class PosLapKeuUnitService {
	
	@Autowired
	private PosLapKeuUnitRepository posLapKeuUnitRepository;
	
	public List<PosLapKeuUnit> findAll() {
		return posLapKeuUnitRepository.findAll();
	}
	
	public PosLapKeuUnit findOne(Integer id) {
		return posLapKeuUnitRepository.findOne(id);
	}
	
	public void save(PosLapKeuUnit posLapKeuUnit) {
		posLapKeuUnitRepository.save(posLapKeuUnit);
	}
	
	public void delete(Integer id) {
		posLapKeuUnitRepository.delete(id);
	}
	
	public PosLapKeuUnit findByPosAndUnit(String posId, String unitId) {
		return posLapKeuUnitRepository.findByPosAndUnit(posId, unitId);
	}

}
