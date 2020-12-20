package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.FasilitasKreditAktifasiModel;
import com.mert.repository.FasilitasKreditAktifasiModelRepository;

@Service
public class FasilitasKreditAktifasiModelService {
	
	@Autowired
	private FasilitasKreditAktifasiModelRepository fasilitasKreditAktifasiModelRepository;
	
	public List<FasilitasKreditAktifasiModel> findAll() {
		return fasilitasKreditAktifasiModelRepository.findAll();
	}
	
	public FasilitasKreditAktifasiModel findOne(String id) {
		return fasilitasKreditAktifasiModelRepository.findOne(id);
	}
	
	public void save(FasilitasKreditAktifasiModel fasilitasKreditAktifasiModel) {
		fasilitasKreditAktifasiModelRepository.save(fasilitasKreditAktifasiModel);
	}
	
	public void delete(String id) {
		fasilitasKreditAktifasiModelRepository.delete(id);
	}

}
