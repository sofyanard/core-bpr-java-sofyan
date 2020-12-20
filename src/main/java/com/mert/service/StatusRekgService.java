package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.StatusRekg;
import com.mert.repository.StatusRekgRepository;

@Service
@Transactional
public class StatusRekgService {
	@Autowired
	private StatusRekgRepository statusRekgRepository;
	
	public StatusRekgService(StatusRekgRepository statusRekgRepository) {
		// TODO Auto-generated constructor stub
		this.statusRekgRepository = statusRekgRepository;
	}
	
	public List<StatusRekg> findAll(){
		List<StatusRekg> statusRekg = new ArrayList<>();
		statusRekg = statusRekgRepository.findAll();
		return statusRekg;
	}
	
	public List<StatusRekg> ListAllStatusRekg(){
		return statusRekgRepository.findAll();
	}
	
	public StatusRekg findByCode(String code) {
		return statusRekgRepository.findOne(code);
	}
	
	
}
