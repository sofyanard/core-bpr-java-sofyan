package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.AppGroup;
import com.mert.repository.AppGroupRepository;

@Service
public class AppGroupService {
	
	@Autowired
	private AppGroupRepository appGroupRepository;
	
	public List<AppGroup> findAll() {
		return appGroupRepository.findAll();
	}
	
	public AppGroup findOne(String id) {
		return appGroupRepository.findOne(id);
	}
	
	public void save(AppGroup appGroup) {
		appGroupRepository.save(appGroup);
	}

}
