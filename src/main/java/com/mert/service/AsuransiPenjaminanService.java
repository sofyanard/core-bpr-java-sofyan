package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.AsuransiPenjaminan;
import com.mert.repository.AsuransiPenjaminanRepository;

@Service
public class AsuransiPenjaminanService {
	
	@Autowired
	private AsuransiPenjaminanRepository asuransiPenjaminanRepository;
	
	public List<AsuransiPenjaminan> findAll() {
		return asuransiPenjaminanRepository.findAll();
	}
	
	public AsuransiPenjaminan findOne(String id) {
		return asuransiPenjaminanRepository.findOne(id);
	}
	
	public void save(AsuransiPenjaminan asuransiPenjaminan) {
		asuransiPenjaminanRepository.save(asuransiPenjaminan);
	}
	
	public void delete(String id) {
		asuransiPenjaminanRepository.delete(id);
	}
	
	public boolean exists(String id) {
		return asuransiPenjaminanRepository.exists(id);
	}

}
