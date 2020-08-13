package com.mert.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.mert.model.Test;
import com.mert.repository.TestRepository;

@Service("testService")
public class TestService {
	
	@Autowired
	private TestRepository testRepository;
	
	public List<Test> listAll() {
		return testRepository.findAll();
	}
	
	public Test getById(int id) {
		return testRepository.findOne(id);
	}
	
	public void save(Test test) {
		testRepository.save(test);
	}
	
	public void delete(int id) {
		testRepository.delete(id);
	}

}
