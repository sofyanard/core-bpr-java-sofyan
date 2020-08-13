package com.mert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.Kategori;
import com.mert.model.Tabungan;
import com.mert.model.User;
import com.mert.service.KategoriService;
import com.mert.service.TabunganService;
import com.mert.service.UserService;

@Controller

@RequestMapping("/admin/tabungans")
public class TabunganController {
	
	@Autowired
	private TabunganService tabunganService;
	
	@Autowired
	private UserService userService;

	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newTabungan() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tabungan", new Tabungan());
		modelAndView.addObject("tabungans", tabunganService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("tabungan");
		return modelAndView;
	}
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allTabungan() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Tabungan());
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("tabungans", tabunganService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("tabungan");
		return modelAndView;
	}
	
	
	
	
	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}
}
