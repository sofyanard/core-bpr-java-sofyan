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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.service.AppUserService;
import com.mert.model.EodUnitStatus;
import com.mert.model.UserBukuBesarKasStatusViewModel;
import com.mert.service.EodUnitStatusService;
import com.mert.service.UnitKasStatusService;
import com.mert.service.EodTanggalService;

@Controller
public class KasirBukaTutupController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private EodUnitStatusService eodUnitStatusService;
	
	@Autowired
	private UnitKasStatusService unitKasStatusService;
	
	@Autowired
	private EodTanggalService eodTanggalService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/kasir/bukakasir", method = RequestMethod.GET)
	public ModelAndView BukaKasir(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		String unitId = getUser().getUnitId().getUnitId();
		EodUnitStatus eodUnitStatusUnit = eodUnitStatusService.getUnitStatusDetail(unitId);
		modelAndView.addObject("eodUnitStatusUnit", eodUnitStatusUnit);
		List<EodUnitStatus> listEodUnitStatusUser = eodUnitStatusService.findUsersByUnit(unitId);
		modelAndView.addObject("listEodUnitStatusUser", listEodUnitStatusUser);
		List<AppUser> listUser = eodUnitStatusService.listAvailableUsersByUnit(unitId);
		modelAndView.addObject("listUser", listUser);
		modelAndView.addObject("unitId", unitId);
		// modelAndView.addObject("oprDate", new Date());
		modelAndView.addObject("oprDate", eodTanggalService.getDate());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.setViewName("kasir/bukakasir");
		return modelAndView;
	}
	
	@RequestMapping(value="/kasir/bukakasir", method = RequestMethod.POST)
	public ModelAndView BukaKasirPost(@RequestParam("UserId") String userId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			eodUnitStatusService.setUserOpen(userId);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/bukakasir?errMsg=" + e.getMessage());
			return modelAndView;
		}
		modelAndView.setViewName("redirect:/kasir/bukakasir");
		return modelAndView;
	}
	
	@RequestMapping(value="/kasir/tutupkasir", method = RequestMethod.GET)
	public ModelAndView TutupKasir(String errMsg) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		String unitId = getUser().getUnitId().getUnitId();
		EodUnitStatus eodUnitStatusUnit = eodUnitStatusService.getUnitStatusDetail(unitId);
		modelAndView.addObject("eodUnitStatusUnit", eodUnitStatusUnit);
		
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
		// modelAndView.addObject("oprDate", new Date());
		modelAndView.addObject("oprDate", eodTanggalService.getDate());
		modelAndView.addObject("errMsg", errMsg);
		modelAndView.setViewName("kasir/tutupkasir");
		return modelAndView;
	}
	
	@RequestMapping(value="/kasir/tutupkasir/{userId}", method = RequestMethod.POST)
	public ModelAndView TutupKasirPost(@PathVariable String userId) {
		ModelAndView modelAndView = new ModelAndView();
		try {
			eodUnitStatusService.setUserClose(userId);
		}
		catch (Exception e) {
			modelAndView.setViewName("redirect:/kasir/tutupkasir?errMsg=" + e.getMessage());
			return modelAndView;
		}
		modelAndView.setViewName("redirect:/kasir/tutupkasir");
		return modelAndView;
	}

}
