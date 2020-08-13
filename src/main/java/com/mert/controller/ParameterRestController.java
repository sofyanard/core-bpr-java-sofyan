package com.mert.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;

import com.mert.model.ParameterProvinsi;
import com.mert.model.ParameterKotaKab;
import com.mert.service.ParameterService;

@RestController
@RequestMapping("/parameter")
public class ParameterRestController {
	
	@Autowired
	private ParameterService parameterService;
	
	@RequestMapping(value="/test", method = RequestMethod.GET)
	public String test(String message) {
		return message;
	}
	
	@RequestMapping(value="/listallprovinsi", method = RequestMethod.GET)
	public List<ParameterProvinsi> listProvinsi() {
		return parameterService.listAllProvinsi();
	}
	
	@RequestMapping(value="/listkotakabbyprov/{provinsicode}", method = RequestMethod.GET)
	public List<ParameterKotaKab> listKotaKabByProv(@PathVariable String provinsicode) {
		return parameterService.listKotaKabByProv(provinsicode);
	}
	
}
