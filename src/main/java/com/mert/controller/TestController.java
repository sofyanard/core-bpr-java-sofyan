package com.mert.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Autowired;

import com.mert.model.Test;
import com.mert.model.User;
import com.mert.service.TestService;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private TestService testService;
	
	@RequestMapping(value="/index", method = RequestMethod.GET)
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("tests", testService.listAll());
		modelAndView.setViewName("test/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("test", new Test());
		modelAndView.setViewName("test/create");
		return modelAndView;
	}
	
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ModelAndView save(Test test) {
		ModelAndView modelAndView = new ModelAndView("redirect:/test/index");
		testService.save(test);
		return modelAndView;
	}
	

	
}
