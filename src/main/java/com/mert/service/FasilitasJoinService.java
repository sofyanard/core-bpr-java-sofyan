package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.FasilitasJoin;
import com.mert.repository.FasilitasJoinRepository;

@Service
public class FasilitasJoinService {
	
	@Autowired
	private FasilitasJoinRepository fasilitasJoinRepository;
	
	public List<FasilitasJoin> findAll() {
		return fasilitasJoinRepository.findAll();
	}
	
	public List<FasilitasJoin> findByNofasilitas(String nofasilitas) {
		return fasilitasJoinRepository.findByNofasilitas(nofasilitas);
	}
	
	public FasilitasJoin findOne(Integer id) {
		return fasilitasJoinRepository.findOne(id);
	}
	
	public void save(FasilitasJoin fasilitasJoin) {
		fasilitasJoinRepository.save(fasilitasJoin);
	}
	
	public void delete(Integer id) {
		fasilitasJoinRepository.delete(id);
	}

}
