package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.PosisiLapKeuUnit;
import com.mert.repository.PosisiLapKeuUnitRepository;

@Service
public class PosisiLapKeuUnitService {
	
	@Autowired
	private PosisiLapKeuUnitRepository posisiLapKeuUnitRepository;
	
	public List<PosisiLapKeuUnit> findAll() {
		return posisiLapKeuUnitRepository.findAll();
	}
	
	public PosisiLapKeuUnit findOne(Integer id) {
		return posisiLapKeuUnitRepository.findOne(id);
	}
	
	public void save(PosisiLapKeuUnit posisiLapKeuUnit) {
		posisiLapKeuUnitRepository.save(posisiLapKeuUnit);
	}
	
	public void delete(Integer id) {
		posisiLapKeuUnitRepository.delete(id);
	}
	
	public PosisiLapKeuUnit findByPosisiAndUnit(String posisiId, String unitId) {
		return posisiLapKeuUnitRepository.findByPosisiAndUnit(posisiId, unitId);
	}

}
