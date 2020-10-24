package com.mert.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppGroup;
import com.mert.model.AppMenu;
import com.mert.model.AppGroupMenu;
import com.mert.service.AppGroupMenuService;
import com.mert.service.AppGroupService;
import com.mert.service.AppMenuService;

@Controller
@RequestMapping("/appgroupmenu")
public class AppGroupMenuController {
	
	@Autowired
	private AppGroupMenuService appGroupMenuService;
	
	@Autowired
	private AppGroupService appGroupService;
	
	@Autowired
	private AppMenuService appMenuService;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index(String GroupId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appGroups", appGroupService.findAll());
		List<AppGroupMenu> appGroupMenus = null;
		if (GroupId != "")
		{
			appGroupMenus = appGroupMenuService.findByGroup(GroupId);
		}
		// else
		// {
		//	appGroupMenus = appGroupMenuService.findAll();
		// }
		modelAndView.addObject("appGroupMenus", appGroupMenus);
		modelAndView.setViewName("appgroupmenu/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appgroupmenu/index");
		appGroupMenuService.delete(id);
		return modelAndView;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appGroupMenu", new AppGroupMenu());
		modelAndView.addObject("appGroups", appGroupService.findAll());
		List<AppMenu> orgAppMenus = appMenuService.findAll();
		List<AppMenu> modAppMenus = new ArrayList<AppMenu>();
		orgAppMenus.forEach(orgAppMenu -> {
			AppMenu modAppMenu = new AppMenu();
			modAppMenu.setMenuId(orgAppMenu.getMenuId());
			modAppMenu.setMenuName(orgAppMenu.getParent() != null ? orgAppMenu.getParent().getMenuName() + " - " + orgAppMenu.getMenuName() : orgAppMenu.getMenuName());
			modAppMenus.add(modAppMenu);
		}); 
		modelAndView.addObject("appMenus", modAppMenus);
		modelAndView.setViewName("appgroupmenu/create");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ModelAndView insert(AppGroupMenu appGroupMenu) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appgroupmenu/index");
		appGroupMenuService.save(appGroupMenu);
		return modelAndView;
	}

}
