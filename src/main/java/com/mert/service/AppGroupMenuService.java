package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.AppGroupMenu;
import com.mert.repository.AppGroupMenuRepository;

@Service
public class AppGroupMenuService {
	
	@Autowired
	private AppGroupMenuRepository appGroupMenuRepository;
	
	public List<AppGroupMenu> findAll() {
		return appGroupMenuRepository.findAll();
	}
	
	public List<AppGroupMenu> findByGroup(String groupId) {
		return appGroupMenuRepository.findByGroup(groupId);
	}
	
	public List<AppGroupMenu> findParentByGroup(String groupId) {
		return appGroupMenuRepository.findParentByGroup(groupId);
	}
	
	public List<AppGroupMenu> findChildByGroupParent(String groupId, String parent) {
		return appGroupMenuRepository.findChildByGroupParent(groupId, parent);
	}
	
	public AppGroupMenu findOne(Integer id) {
		return appGroupMenuRepository.findOne(id);
	}
	
	public void save(AppGroupMenu appGroupMenu) {
		appGroupMenuRepository.save(appGroupMenu);
	}
	
	public void delete(Integer id) {
		appGroupMenuRepository.delete(id);
	}

}
