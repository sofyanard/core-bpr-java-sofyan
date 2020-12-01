package com.mert.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.FasilitasKreditApprovalModel;
import com.mert.repository.FasilitasKreditApprovalModelRepository;

@Service
public class FasilitasKreditApprovalModelService {
	
	@Autowired
	private FasilitasKreditApprovalModelRepository fasilitasKreditApprovalModelRepository;
	
	public List<FasilitasKreditApprovalModel> findAll() {
		return fasilitasKreditApprovalModelRepository.findAll();
	}
	
	public FasilitasKreditApprovalModel findOne(String id) {
		return fasilitasKreditApprovalModelRepository.findOne(id);
	}
	
	public void save(FasilitasKreditApprovalModel fasilitasKreditApprovalModel) {
		fasilitasKreditApprovalModelRepository.save(fasilitasKreditApprovalModel);
	}
	
	public void delete(String id) {
		fasilitasKreditApprovalModelRepository.delete(id);
	}

}
