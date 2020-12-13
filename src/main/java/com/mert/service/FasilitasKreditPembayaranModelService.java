package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.FasilitasKreditPembayaranModel;
import com.mert.repository.FasilitasKreditPembayaranModelRepository;

@Service
public class FasilitasKreditPembayaranModelService {
	
	@Autowired
	private FasilitasKreditPembayaranModelRepository fasilitasKreditPembayaranModelRepository;
	
	public List<FasilitasKreditPembayaranModel> findAll() {
		return fasilitasKreditPembayaranModelRepository.findAll();
	}
	
	public FasilitasKreditPembayaranModel findOne(String id) {
		return fasilitasKreditPembayaranModelRepository.findOne(id);
	}
	
	public void save(FasilitasKreditPembayaranModel fasilitasKreditPembayaranModel) {
		fasilitasKreditPembayaranModelRepository.save(fasilitasKreditPembayaranModel);
	}
	
	public void delete(String id) {
		fasilitasKreditPembayaranModelRepository.delete(id);
	}

}
