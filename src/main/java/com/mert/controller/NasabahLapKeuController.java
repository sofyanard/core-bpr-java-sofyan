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
import com.mert.model.NasabahLapKeu;
import com.mert.model.NasabahPengurus;
import com.mert.service.UserService;
import com.mert.service.NasabahLapKeuService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/nasabah/lapkeu")
public class NasabahLapKeuController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NasabahLapKeuService nasabahLapKeuService;
	
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
		List<NasabahLapKeu> listLapKeu = nasabahLapKeuService.listByNonasabah(nonasabah);
		modelAndView.addObject("listLapKeu", listLapKeu);
		modelAndView.setViewName("nasabah/lapkeuindex");
		return modelAndView;
	}
	
	@RequestMapping(value="/create/{nonasabah}", method = RequestMethod.GET)
	public ModelAndView create(@PathVariable Long nonasabah) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("nonasabah", nonasabah);
		NasabahLapKeu nasabahLapKeu = new NasabahLapKeu();
		modelAndView.addObject("nasabahLapKeu", nasabahLapKeu);
		modelAndView.setViewName("nasabah/lapkeucreate");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert/{nonasabah}", method = RequestMethod.POST)
	public ModelAndView insert(@PathVariable Long nonasabah, NasabahLapKeu nasabahLapKeu) {
		nasabahLapKeu.setNonasabah(nonasabah);
		nasabahLapKeuService.save(nasabahLapKeu);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/lapkeu/index/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{nonasabah}/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable Long nonasabah, @PathVariable int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("nonasabah", nonasabah);
		NasabahLapKeu nasabahLapKeu = nasabahLapKeuService.findById(id);
		modelAndView.addObject("nasabahLapKeu", nasabahLapKeu);
		modelAndView.setViewName("nasabah/lapkeuedit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update/{nonasabah}", method = RequestMethod.POST)
	public ModelAndView update(@PathVariable Long nonasabah, NasabahLapKeu nasabahLapKeu) {
		nasabahLapKeu.setNonasabah(nonasabah);
		nasabahLapKeuService.save(nasabahLapKeu);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/lapkeu/index/" + nonasabah);
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{nonasabah}/{id}", method = RequestMethod.GET)
	public ModelAndView delete(@PathVariable Long nonasabah, @PathVariable int id) {
		nasabahLapKeuService.delete(id);
		ModelAndView modelAndView = new ModelAndView("redirect:/nasabah/lapkeu/index/" + nonasabah);
		return modelAndView;
	}

}
