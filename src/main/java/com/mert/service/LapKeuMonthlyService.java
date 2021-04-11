package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.LapKeuMonthly;
import com.mert.repository.LapKeuMonthlyRepository;

@Service
public class LapKeuMonthlyService {
	
	@Autowired
	private LapKeuMonthlyRepository lapKeuMonthlyRepository;
	
	public List<LapKeuMonthly> findAll() {
		return lapKeuMonthlyRepository.findAll();
	}
	
	public LapKeuMonthly findOne(Integer id) {
		return lapKeuMonthlyRepository.findOne(id);
	}
	
	public void save(LapKeuMonthly lapKeuMonthly) {
		lapKeuMonthlyRepository.save(lapKeuMonthly);
	}
	
	public void delete(Integer id) {
		lapKeuMonthlyRepository.delete(id);
	}
	
	public List<LapKeuMonthly> findByTanggal(String strDate) {
		return lapKeuMonthlyRepository.findByTanggal(strDate);
	}
	
	public List<LapKeuMonthly> findByTanggalAndUnitAndJenis(String strDate, String unitId, String jenisId) {
		return lapKeuMonthlyRepository.findByTanggalAndUnitAndJenis(strDate, unitId, jenisId);
	}
	
	public List<String> listTanggal() {
		return lapKeuMonthlyRepository.listTanggal();
	}
	
	public List<LapKeuMonthly> customEodLapKeu() {
		return lapKeuMonthlyRepository.customEodLapKeu();
	}
	
	public Integer customEodLapKeuCount() {
		return lapKeuMonthlyRepository.customEodLapKeuCount();
	}

}
