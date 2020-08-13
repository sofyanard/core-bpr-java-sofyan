package com.mert.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.Kategori;
import com.mert.model.Role;
import com.mert.model.Task;
import com.mert.model.User;
import com.mert.service.KategoriService;
import com.mert.service.TaskService;
import com.mert.service.UserService;
import com.mert.service.UserTaskService;

@Controller

@RequestMapping("/admin/kategoris")
public class KategoriController {
	
	@Autowired
	private KategoriService kategoriService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/new", method = RequestMethod.GET)
	public ModelAndView newKategori() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("kategori", new Kategori());
		modelAndView.addObject("kategoris", kategoriService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_NEW");
		modelAndView.setViewName("kategori");
		return modelAndView;
	}
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView saveKategori(@Valid Kategori kategori, BindingResult bindingResult) {
		kategoriService.save(kategori);
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/kategoris/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		return modelAndView;
	}
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ModelAndView allKategori() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("rule", new Kategori());
		//POINT=7 http://stackoverflow.com/questions/22364886/neither-bindingresult-nor-plain-target-object-for-bean-available-as-request-attr
		modelAndView.addObject("kategoris", kategoriService.findAll());
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_ALL");
		modelAndView.setViewName("kategori");
		return modelAndView;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView updateKategori(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("personel_type", new Kategori());
		modelAndView.addObject("kategori", kategoriService.findKategori(id));
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		modelAndView.addObject("mode", "MODE_UPDATE");
		modelAndView.setViewName("kategori");
		return modelAndView;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteKategori(@RequestParam int id) {
		ModelAndView modelAndView = new ModelAndView("redirect:/admin/kategoris/all");
		modelAndView.addObject("auth", getUser());
		modelAndView.addObject("control", getUser().getRole().getRole());
		kategoriService.delete(id);
		return modelAndView;
	}

	private User getUser(){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		return user;
	}

}
