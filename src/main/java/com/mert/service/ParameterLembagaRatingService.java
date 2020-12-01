package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.ParameterLembagaRating;
import com.mert.repository.ParameterLembagaRatingRepository;

@Service
public class ParameterLembagaRatingService {
	
	@Autowired
	private ParameterLembagaRatingRepository parameterLembagaRatingRepository;
	
	public List<ParameterLembagaRating> findAll() {
		return parameterLembagaRatingRepository.findAll();
	}
	
	public ParameterLembagaRating findOne(String id) {
		return parameterLembagaRatingRepository.findOne(id);
	}
	
	public void save(ParameterLembagaRating parameterLembagaRating) {
		parameterLembagaRatingRepository.save(parameterLembagaRating);
	}
	
	public void delete(String id) {
		parameterLembagaRatingRepository.delete(id);
	}

}
