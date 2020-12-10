package com.mert.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mert.model.UnitKasStatus;
import com.mert.repository.UnitKasStatusRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UnitKasStatusService {
	
	@Autowired
	private UnitKasStatusRepository unitKasStatusRepository;
	
private String strToday;
	
	public UnitKasStatusService() {
		Date today = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strToday = formatter.format(today);
		this.strToday = strToday;
	}
	
	public List<UnitKasStatus> findAll() {
		return unitKasStatusRepository.findAll();
	}
	
	public UnitKasStatus findOne(Integer id) {
		return unitKasStatusRepository.findOne(id);
	}
	
	public void save(UnitKasStatus unitKasStatus) {
		unitKasStatusRepository.save(unitKasStatus);
	}
	
	public void delete(Integer id) {
		unitKasStatusRepository.delete(id);
	}
	
	public List<UnitKasStatus> findByUnit(String unitId) {
		return unitKasStatusRepository.findByUnitAndDate(unitId, strToday);
	}
	
	public UnitKasStatus findByUser(String userId) {
		return unitKasStatusRepository.findByUserAndDate(userId, strToday);
	}

}
