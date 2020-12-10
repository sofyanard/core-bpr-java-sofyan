package com.mert.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.model.EodUnitStatus;
import com.mert.model.UserBukuBesarKasStatusViewModel;
import com.mert.service.EodUnitStatusService;
import com.mert.service.UnitKasStatusService;

@Controller
public class AkuntingBukaTutupUnitController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private EodUnitStatusService eodUnitStatusService;
	
	@Autowired
	private UnitKasStatusService unitKasStatusService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/akunting/bukaunit", method = RequestMethod.GET)
	public ModelAndView BukaUnit(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		String unitId = getUser().getUnitId().getUnitId();
		EodUnitStatus eodUnitStatus = eodUnitStatusService.getUnitStatusDetail(unitId);
		modelAndView.addObject("eodUnitStatus", eodUnitStatus);
		modelAndView.addObject("unitId", unitId);
		modelAndView.addObject("oprDate", new Date());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.setViewName("akunting/bukaunit");
		return modelAndView;
	}
	
	@RequestMapping(value="/akunting/bukaunit/{unitId}", method = RequestMethod.POST)
	public ModelAndView BukaUnitPost(@PathVariable String unitId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			eodUnitStatusService.setUnitOpen(unitId);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/akunting/bukaunit?errMsg=" + e.getMessage());
			return modelAndView;
		}
		modelAndView.setViewName("redirect:/akunting/bukaunit");
		return modelAndView;
	}
	
	@RequestMapping(value="/akunting/tutupunit", method = RequestMethod.GET)
	public ModelAndView TutupUnit(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		String unitId = getUser().getUnitId().getUnitId();
		EodUnitStatus eodUnitStatus = eodUnitStatusService.getUnitStatusDetail(unitId);
		modelAndView.addObject("eodUnitStatus", eodUnitStatus);
		
		List<UserBukuBesarKasStatusViewModel> listUserBukuBesarKasStatusViewModel = new ArrayList<UserBukuBesarKasStatusViewModel>();
		
		List<EodUnitStatus> listEodUnitStatusUser = eodUnitStatusService.findUsersByUnit(unitId);
		for (EodUnitStatus eodUnitStatusUser : listEodUnitStatusUser) {
			
			UserBukuBesarKasStatusViewModel user = new UserBukuBesarKasStatusViewModel();
			user.setEodUnitStatus(eodUnitStatusUser);
			user.setUnitKasStatus(unitKasStatusService.findByUser(eodUnitStatusUser.getUserId().getUserId()));
			
			listUserBukuBesarKasStatusViewModel.add(user);
		}
		
		modelAndView.addObject("listUser", listUserBukuBesarKasStatusViewModel);
		
		modelAndView.addObject("unitId", unitId);
		modelAndView.addObject("oprDate", new Date());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.setViewName("akunting/tutupunit");
		return modelAndView;
	}
	
	@RequestMapping(value="/akunting/tutupunit/{unitId}", method = RequestMethod.POST)
	public ModelAndView TutupUnitPost(@PathVariable String unitId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			eodUnitStatusService.setUnitClose(unitId);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/akunting/tutupunit?errMsg=" + e.getMessage());
			return modelAndView;
		}
		modelAndView.setViewName("redirect:/akunting/tutupunit");
		return modelAndView;
	}
	
	@RequestMapping(value="/akunting/paksatutupkasir/{userId}", method = RequestMethod.POST)
	public ModelAndView PaksaTutupKasirPost(@PathVariable String userId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			eodUnitStatusService.forceUserClose(userId);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/akunting/tutupunit?errMsg=" + e.getMessage());
			return modelAndView;
		}
		modelAndView.setViewName("redirect:/akunting/tutupunit");
		return modelAndView;
	}
	
}
