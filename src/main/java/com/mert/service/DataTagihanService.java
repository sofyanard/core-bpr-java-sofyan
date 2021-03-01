package com.mert.service;

import java.util.List;

import com.mert.model.DataTagihan;
import com.mert.repository.DataTagihanRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataTagihanService {
	
	@Autowired
	private DataTagihanRepository dataTagihanRepository;
	
	public List<DataTagihan> findAll() {
		return dataTagihanRepository.findAll();
	}
	
	public DataTagihan findOne(Integer id) {
		return dataTagihanRepository.findOne(id);
	}
	
	public void save(DataTagihan dataTagihan) {
		dataTagihanRepository.save(dataTagihan);
	}
	
	public void delete(Integer id) {
		dataTagihanRepository.delete(id);
	}
	
	public List<DataTagihan> findByNoRekening(String noRekening) {
		return dataTagihanRepository.findByNoRekening(noRekening);
	}
	
	public DataTagihan findByNoRekeningAndDueDate(String noRekening, String strDate) {
		return dataTagihanRepository.findByNoRekeningAndDueDate(noRekening, strDate);
	}

}
