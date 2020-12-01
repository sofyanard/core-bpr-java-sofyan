package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterBank;
import com.mert.repository.ParameterBankRepository;

@Service
public class ParameterBankService {
	
	@Autowired
	private ParameterBankRepository parameterBankRepository;
	
	public List<ParameterBank> findAll() {
		return parameterBankRepository.findAll();
	}
	
	public ParameterBank findOne(String id) {
		return parameterBankRepository.findOne(id);
	}
	
	public void save(ParameterBank parameterBank) {
		parameterBankRepository.save(parameterBank);
	}

}
