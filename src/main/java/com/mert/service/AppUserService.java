package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.AppUser;
import com.mert.repository.AppUserRepository;

import com.mert.model.AppGroupMenu;
import com.mert.model.AppChildMenuModel;
import com.mert.model.AppParentChildMenuModel;

@Service
public class AppUserService {
	
	@Autowired
	private AppUserRepository appUserRepository;
	
	@Autowired
	private AppGroupMenuService appGroupMenuService;
	
	public List<AppUser> findAll() {
		return appUserRepository.findAll();
	}
	
	public AppUser findOne(String id) {
		return appUserRepository.findOne(id);
	}
	
	public void save(AppUser appUser) {
		appUserRepository.save(appUser);
	}
	
	public List<AppParentChildMenuModel> GetUserMenu(AppUser appUser) {
		List<AppParentChildMenuModel> listAppParentChildMenuModel = new ArrayList<AppParentChildMenuModel>();
		
		List<AppGroupMenu> listParentMenu = new ArrayList<AppGroupMenu>();
		listParentMenu = appGroupMenuService.findParentByGroup(appUser.getGroupId().getGroupId());
		
		listParentMenu.forEach(parentMenu -> {
			AppParentChildMenuModel appParentChildMenuModel = new AppParentChildMenuModel();
			appParentChildMenuModel.MenuId = parentMenu.getMenuId().getMenuId();
			appParentChildMenuModel.MenuName = parentMenu.getMenuId().getMenuName();
			appParentChildMenuModel.Route = parentMenu.getMenuId().getRoute();
			appParentChildMenuModel.Icon = parentMenu.getMenuId().getIcon();
			appParentChildMenuModel.Param = parentMenu.getMenuId().getParam();
			appParentChildMenuModel.Children = new ArrayList<AppChildMenuModel>();
			
			List<AppGroupMenu> listChildMenu = new ArrayList<AppGroupMenu>();
			listChildMenu = appGroupMenuService.findChildByGroupParent(appUser.getGroupId().getGroupId(), appParentChildMenuModel.MenuId);
			
			listChildMenu.forEach(childMenu -> {
				AppChildMenuModel appChildMenuModel = new AppChildMenuModel();
				appChildMenuModel.MenuId = childMenu.getMenuId().getMenuId();
				appChildMenuModel.MenuName = childMenu.getMenuId().getMenuName();
				appChildMenuModel.Route = childMenu.getMenuId().getRoute();
				appChildMenuModel.Icon = childMenu.getMenuId().getIcon();
				appChildMenuModel.Param = childMenu.getMenuId().getParam();
				
				appParentChildMenuModel.Children.add(appChildMenuModel);
			});
			
			listAppParentChildMenuModel.add(appParentChildMenuModel);
		});
		
		return listAppParentChildMenuModel;
	}

}
