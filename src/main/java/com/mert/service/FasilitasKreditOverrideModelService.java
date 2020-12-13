package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.FasilitasKreditOverrideModel;
import com.mert.repository.FasilitasKreditOverrideModelRepository;

@Service
public class FasilitasKreditOverrideModelService {
	
	@Autowired
	private FasilitasKreditOverrideModelRepository fasilitasKreditOverrideModelRepository;
	
	public List<FasilitasKreditOverrideModel> findAll() {
		return fasilitasKreditOverrideModelRepository.findAll();
	}
	
	public FasilitasKreditOverrideModel findOne(String id) {
		return fasilitasKreditOverrideModelRepository.findOne(id);
	}
	
	public void save(FasilitasKreditOverrideModel fasilitasKreditOverrideModel) {
		fasilitasKreditOverrideModelRepository.save(fasilitasKreditOverrideModel);
	}
	
	public void delete(String id) {
		fasilitasKreditOverrideModelRepository.delete(id);
	}
	
	public FasilitasKreditOverrideModel findByNoRef(String noref) {
		return fasilitasKreditOverrideModelRepository.findByNoRef(noref);
	}

}
