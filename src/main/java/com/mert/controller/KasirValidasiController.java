package com.mert.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUser;
import com.mert.model.TranHistory;
import com.mert.service.AppUserService;
import com.mert.service.TranHistoryService;

@Controller
@RequestMapping("/kasir/validasi")
public class KasirValidasiController {
	
	@Autowired
	private AppUserService appUserService;
	
	@Autowired
	private TranHistoryService tranHistoryService;
	
	private AppUser getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		AppUser user = appUserService.findOne(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/printtext/{trxRefNo}", method = RequestMethod.GET)
	public ModelAndView PrintText(@PathVariable UUID trxRefNo) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("userMenus", appUserService.GetUserMenu(getUser()));
		
		List<TranHistory> listTranHistory = tranHistoryService.findByTranRef(trxRefNo);
		modelAndView.addObject("listTranHistory", listTranHistory);
		modelAndView.addObject("trxRefNo", trxRefNo);
		
		modelAndView.setViewName("kasir/validasiprinttext");
		return modelAndView;
	}

}
