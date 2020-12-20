package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterSegment;
import com.mert.repository.ParameterSegmentRepository;

@Service
public class ParameterSegmentService {
	
	@Autowired
	private ParameterSegmentRepository parameterSegmentRepository;
	
	public List<ParameterSegment> findAll() {
		return parameterSegmentRepository.findAll();
	}
	
	public ParameterSegment findOne(String id) {
		return parameterSegmentRepository.findOne(id);
	}
	
	public void save(ParameterSegment parameterSegment) {
		parameterSegmentRepository.save(parameterSegment);
	}
	
	public void delete(String id) {
		parameterSegmentRepository.delete(id);
	}

}
