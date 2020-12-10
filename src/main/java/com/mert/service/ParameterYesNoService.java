package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterYesNo;
import com.mert.repository.ParameterYesNoRepository;

@Service
public class ParameterYesNoService {
	
	@Autowired
	private ParameterYesNoRepository parameterYesNoRepository;
	
	public List<ParameterYesNo> findAll() {
		return parameterYesNoRepository.findAll();
	}
	
	public ParameterYesNo findOne(String id) {
		return parameterYesNoRepository.findOne(id);
	}
	
	public void save(ParameterYesNo parameterYesNo) {
		parameterYesNoRepository.save(parameterYesNo);
	}
	
	public void delete(String id) {
		parameterYesNoRepository.delete(id);
	}

}
