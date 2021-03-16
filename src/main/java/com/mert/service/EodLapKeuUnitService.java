package com.mert.service;

import java.util.List;

import com.mert.model.EodLapKeuUnit;
import com.mert.repository.EodLapKeuUnitRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EodLapKeuUnitService {
	
	@Autowired
	private EodLapKeuUnitRepository eodLapKeuUnitRepository;
	
	public List<EodLapKeuUnit> findAll() {
		return eodLapKeuUnitRepository.findAll();
	}
	
	public EodLapKeuUnit findOne(Integer id) {
		return eodLapKeuUnitRepository.findOne(id);
	}
	
	public void save(EodLapKeuUnit eodLapKeuUnit) {
		eodLapKeuUnitRepository.save(eodLapKeuUnit);
	}
	
	public void delete(Integer id) {
		eodLapKeuUnitRepository.delete(id);
	}
	
	public List<EodLapKeuUnit> findByLapKeu(String lapKeuId) {
		return eodLapKeuUnitRepository.findByLapKeu(lapKeuId);
	}

}
