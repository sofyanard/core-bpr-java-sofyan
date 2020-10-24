package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.AppUnit;
import com.mert.repository.AppUnitRepository;

@Service
public class AppUnitService {
	
	@Autowired
	private AppUnitRepository appUnitRepository;
	
	public List<AppUnit> findAll() {
		return appUnitRepository.findAll();
	}
	
	public AppUnit findOne(String id) {
		return appUnitRepository.findOne(id);
	}
	
	public void save(AppUnit appUnit) {
		appUnitRepository.save(appUnit);
	}
}
