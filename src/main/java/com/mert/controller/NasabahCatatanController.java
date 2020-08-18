package com.mert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.User;
import com.mert.model.NasabahCatatan;
import com.mert.service.UserService;
import com.mert.service.NasabahCatatanService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/nasabah/catatan")
public class NasabahCatatanController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NasabahCatatanService nasabahCatatanService;
	
	@Autowired
	private ParameterService parameterService;
	
	
	
	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}
	
	@RequestMapping(value="/index/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView index(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("nonasabah", nonasabah);
		NasabahCatatan nasabahCatatan = nasabahCatatanService.findById(nonasabah);
		if (nasabahCatatan == null) {
			nasabahCatatan = new NasabahCatatan();
		}
		modelAndView.addObject("nasabahCatatan", nasabahCatatan);
		modelAndView.addObject("kategoris", parameterService.listAllKategoriCatatan());
		modelAndView.setViewName("nasabah/catatanindex");
		return modelAndView;
	}
	
	@RequestMapping(value="/save/{nonasabah}", method = RequestMethod.POST)
	public ModelAndView save(@PathVariable Long nonasabah, NasabahCatatan nasabahCatatan) {
		nasabahCatatan.setNonasabah(nonasabah);
		nasabahCatatanService.save(nasabahCatatan);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/catatan/index/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long nonasabah) {
		nasabahCatatanService.delete(nonasabah);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/catatan/index/" + nonasabah);
		return modelAndView;
	}

}
