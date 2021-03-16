package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.SubPosLapKeuUnit;
import com.mert.repository.SubPosLapKeuUnitRepository;

@Service
public class SubPosLapKeuUnitService {
	
	@Autowired
	private SubPosLapKeuUnitRepository subPosLapKeuUnitRepository;
	
	public List<SubPosLapKeuUnit> findAll() {
		return subPosLapKeuUnitRepository.findAll();
	}
	
	public SubPosLapKeuUnit findOne(Integer id) {
		return subPosLapKeuUnitRepository.findOne(id);
	}
	
	public void save(SubPosLapKeuUnit subPosLapKeuUnit) {
		subPosLapKeuUnitRepository.save(subPosLapKeuUnit);
	}
	
	public void delete(Integer id) {
		subPosLapKeuUnitRepository.delete(id);
	}
	
	public SubPosLapKeuUnit findBySubPosAndUnit(String subPosId, String unitId) {
		return subPosLapKeuUnitRepository.findBySubPosAndUnit(subPosId, unitId);
	}

}
