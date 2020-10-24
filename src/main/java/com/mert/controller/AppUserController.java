package com.mert.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.service.AppUnitService;
import com.mert.service.AppGroupService;

@Controller
@RequestMapping("/appuser")
public class AppUserController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private AppUnitService appUnitService;
	
	@Autowired
	private AppGroupService appGroupService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appUsers", appUserService.findAll());
		modelAndView.setViewName("appuser/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appUser", new AppUser());
		modelAndView.addObject("appUnits", appUnitService.findAll());
		modelAndView.addObject("appGroups", appGroupService.findAll());
		modelAndView.setViewName("appuser/create");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ModelAndView insert(AppUser appUser) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appuser/index");
		appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
		Date now = new Date();
		appUser.setIsActive("1");
		appUser.setIsLogin("0");
		appUser.setIsRevoke("0");
		appUser.setCreatedDate(now);
		appUser.setCreatedBy("");
		appUserService.save(appUser);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		AppUser appUser = appUserService.findOne(id);
		modelAndView.addObject("appUser", appUser);
		modelAndView.addObject("appUnits", appUnitService.findAll());
		modelAndView.addObject("appGroups", appGroupService.findAll());
		modelAndView.setViewName("appuser/edit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView update(AppUser inputAppUser) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appuser/index");
		AppUser existAppUser = appUserService.findOne(inputAppUser.getUserId());
		inputAppUser.setIsActive(existAppUser.getIsActive());
		inputAppUser.setIsLogin(existAppUser.getIsLogin());
		inputAppUser.setIsRevoke(existAppUser.getIsRevoke());
		inputAppUser.setFalsePwdCount(existAppUser.getFalsePwdCount());
		inputAppUser.setCreatedDate(existAppUser.getCreatedDate());
		inputAppUser.setCreatedBy(existAppUser.getCreatedBy());
		inputAppUser.setExpiredDate(existAppUser.getExpiredDate());
		inputAppUser.setLastLoginDate(existAppUser.getLastLoginDate());
		inputAppUser.setLastLoginHost(existAppUser.getLastLoginHost());
		inputAppUser.setPassword(bCryptPasswordEncoder.encode(inputAppUser.getPassword()));
		Date now = new Date();
		inputAppUser.setUpdatedDate(now);
		inputAppUser.setUpdatedBy("");
		appUserService.save(inputAppUser);
		return modelAndView;
	}

}
