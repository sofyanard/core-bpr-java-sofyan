package com.mert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.PemeliharaanTabungan;
import com.mert.model.Tabungan;
import com.mert.model.User;
import com.mert.service.PemeliharaanTabunganService;
import com.mert.service.UserService;

@Controller
@RequestMapping("/admin/pemeliharaans")
public class PemeliharaanTabunganController {
	
	@Autowired
	private PemeliharaanTabunganService pemeliharaanTabunganService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newPemeliharaanTabungan() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("pemeliharaantabungan", new PemeliharaanTabungan());
		modelAndView.addObject("pemeliharaans", pemeliharaanTabunganService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("pemeliharaan_tabungan");
		return modelAndView;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allPemeliharaanTabungan() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new PemeliharaanTabungan());
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("pemeliharaans", pemeliharaanTabunganService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("pemeliharaan_tabungan");
		return modelAndView;
	}
	
	
	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}

}
 