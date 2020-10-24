package com.mert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppGroup;
import com.mert.service.AppGroupService;

@Controller
@RequestMapping("/appgroup")
public class AppGroupController {
	
	@Autowired
	private AppGroupService appGroupService;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appGroups", appGroupService.findAll());
		modelAndView.setViewName("appgroup/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appGroup", new AppGroup());
		modelAndView.setViewName("appgroup/create");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ModelAndView insert(AppGroup appGroup) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appgroup/index");
		appGroupService.save(appGroup);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		AppGroup appGroup = appGroupService.findOne(id);
		modelAndView.addObject("appGroup", appGroup);
		modelAndView.setViewName("appgroup/edit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView update(AppGroup appGroup) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appgroup/index");
		appGroupService.save(appGroup);
		return modelAndView;
	}

}
