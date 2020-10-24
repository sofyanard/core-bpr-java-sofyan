package com.mert.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mert.model.AppUnit;
import com.mert.service.AppUnitService;
import com.mert.service.ParameterService;

@Controller
@RequestMapping("/appunit")
public class AppUnitController {
	
	@Autowired
	private AppUnitService appUnitService;
	
	@Autowired
	private ParameterService parameterService;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appUnits", appUnitService.findAll());
		modelAndView.setViewName("appunit/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("appUnit", new AppUnit());
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.setViewName("appunit/create");
		return modelAndView;
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ModelAndView insert(AppUnit appUnit) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appunit/index");
		appUnitService.save(appUnit);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public ModelAndView edit(@PathVariable String id) {
		ModelAndView modelAndView = new ModelAndView();
		AppUnit appUnit = appUnitService.findOne(id);
		modelAndView.addObject("appUnit", appUnit);
		modelAndView.addObject("provinsis", parameterService.listAllProvinsi());
		modelAndView.addObject("kotas", parameterService.listKotaKabByProv(appUnit.getPropinsiId().getProvinsicode()));
		modelAndView.setViewName("appunit/edit");
		return modelAndView;
	}
	
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public ModelAndView update(AppUnit appUnit) {
		ModelAndView modelAndView = new ModelAndView("redirect:/appunit/index");
		appUnitService.save(appUnit);
		return modelAndView;
	}

}
