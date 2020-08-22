package com.mert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.User;
import com.mert.model.NasabahPengurus;
import com.mert.service.UserService;
import com.mert.service.NasabahPengurusService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/nasabah/pengurus")
public class NasabahPengurusController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NasabahPengurusService nasabahPengurusService;
	
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
		List<NasabahPengurus> listPengurus = nasabahPengurusService.listByNonasabah(nonasabah);
		modelAndView.addObject("listPengurus", listPengurus);
		modelAndView.setViewName("nasabah/pengurusindex");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView create(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("nonasabah", nonasabah);
		NasabahPengurus nasabahPengurus = new NasabahPengurus();
		modelAndView.addObject("nasabahPengurus", nasabahPengurus);
		modelAndView.addObject("jabatans", parameterService.listSandiBIOJKJabatan());
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("genders", parameterService.ListAllGender());
		modelAndView.setViewName("nasabah/penguruscreate");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nonasabah}", method = RequestMethod.POST)
	public ModelAndView insert(@PathVariable Long nonasabah, NasabahPengurus nasabahPengurus) {
		nasabahPengurus.setNonasabah(nonasabah);
		nasabahPengurusService.save(nasabahPengurus);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/pengurus/index/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{nonasabah}/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long nonasabah, @PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("nonasabah", nonasabah);
		NasabahPengurus nasabahPengurus = nasabahPengurusService.findById(id);
		modelAndView.addObject("nasabahPengurus", nasabahPengurus);
		modelAndView.addObject("jabatans", parameterService.listSandiBIOJKJabatan());
		modelAndView.addObject("jenisids", parameterService.listAllJenisID());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(nasabahPengurus.getProvinsi()));
		modelAndView.addObject("genders", parameterService.ListAllGender());
		modelAndView.setViewName("nasabah/pengurusedit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/{nonasabah}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable Long nonasabah, NasabahPengurus nasabahPengurus) {
		nasabahPengurus.setNonasabah(nonasabah);
		nasabahPengurusService.save(nasabahPengurus);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/pengurus/index/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{nonasabah}/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long nonasabah, @PathVariable int id) {
		nasabahPengurusService.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/pengurus/index/" + nonasabah);
		return modelAndView;
	}

}
