package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.LapKeuDaily;
import com.mert.repository.LapKeuDailyRepository;

@Service
public class LapKeuDailyService {
	
	@Autowired
	private LapKeuDailyRepository lapKeuDailyRepository;
	
	public List<LapKeuDaily> findAll() {
		return lapKeuDailyRepository.findAll();
	}
	
	public LapKeuDaily findOne(Integer id) {
		return lapKeuDailyRepository.findOne(id);
	}
	
	public void save(LapKeuDaily lapKeuDaily) {
		lapKeuDailyRepository.save(lapKeuDaily);
	}
	
	public void delete(Integer id) {
		lapKeuDailyRepository.delete(id);
	}
	
	public void deleteAll() {
		lapKeuDailyRepository.deleteAll();
	}
	
	public List<LapKeuDaily> findByTanggal(String strDate) {
		return lapKeuDailyRepository.findByTanggal(strDate);
	}
	
	public List<LapKeuDaily> findByUnitAndJenis(String unitId, String jenisId) {
		return lapKeuDailyRepository.findByUnitAndJenis(unitId, jenisId);
	}
	
	public List<LapKeuDaily> customEodLapKeu() {
		return lapKeuDailyRepository.customEodLapKeu();
	}
	
	public Integer customEodLapKeuCount() {
		return lapKeuDailyRepository.customEodLapKeuCount();
	}

}
