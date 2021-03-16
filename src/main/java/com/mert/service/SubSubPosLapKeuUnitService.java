package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.SubSubPosLapKeuUnit;
import com.mert.repository.SubSubPosLapKeuUnitRepository;

@Service
public class SubSubPosLapKeuUnitService {
	
	@Autowired
	private SubSubPosLapKeuUnitRepository subSubPosLapKeuUnitRepository;
	
	public List<SubSubPosLapKeuUnit> findAll() {
		return subSubPosLapKeuUnitRepository.findAll();
	}
	
	public SubSubPosLapKeuUnit findOne(Integer id) {
		return subSubPosLapKeuUnitRepository.findOne(id);
	}
	
	public void save(SubSubPosLapKeuUnit subSubPosLapKeuUnit) {
		subSubPosLapKeuUnitRepository.save(subSubPosLapKeuUnit);
	}
	
	public void delete(Integer id) {
		subSubPosLapKeuUnitRepository.delete(id);
	}
	
	public SubSubPosLapKeuUnit findBySubSubPosAndUnit(String subSubPosId, String unitId) {
		return subSubPosLapKeuUnitRepository.findBySubSubPosAndUnit(subSubPosId, unitId);
	}

}
