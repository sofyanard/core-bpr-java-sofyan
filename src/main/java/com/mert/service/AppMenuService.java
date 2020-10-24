package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.AppMenu;
import com.mert.repository.AppMenuRepository;

@Service
public class AppMenuService {
	
	@Autowired
	private AppMenuRepository appMenuRepository;
	
	public List<AppMenu> findAll() {
		return appMenuRepository.findAll();
	}
	
	public List<AppMenu> findByLevel(Integer level) {
		return appMenuRepository.findByLevel(level);
	}
	
	public List<AppMenu> findByParent(String parent) {
		return appMenuRepository.findByParent(parent);
	}
	
	public List<AppMenu> findByLevelAndParent(Integer level, String parent) {
		return appMenuRepository.findByLevelAndParent(level, parent);
	}
	
	public AppMenu findOne(String id) {
		return appMenuRepository.findOne(id);
	}
	
	public void save(AppMenu appMenu) {
		appMenuRepository.save(appMenu);
	}

}
