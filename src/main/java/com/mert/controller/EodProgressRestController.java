package com.mert.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mert.model.EodProgress;
import com.mert.service.EodProgressService;

@RestController
@RequestMapping("/eodprogress")
public class EodProgressRestController {
	
	@Autowired
	private EodProgressService eodProgressService;
	
	@RequestMapping(value="", method = RequestMethod.GET)
	public List<EodProgress> GetAll() {
		return eodProgressService.findAll();
	}
	
	@RequestMapping(value="/{kodeEod}", method = RequestMethod.GET)
	public EodProgress GetOne(@PathVariable String kodeEod) {
		return eodProgressService.findOne(kodeEod);
	}

}
