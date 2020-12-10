package com.mert.service;

import java.util.List;

import com.mert.model.EodUnitStatusType;
import com.mert.repository.EodUnitStatusTypeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EodUnitStatusTypeService {
	
	@Autowired
	private EodUnitStatusTypeRepository eodUnitStatusTypeRepository;
	
	public List<EodUnitStatusType> findAll() {
		return eodUnitStatusTypeRepository.findAll();
	}
	
	public EodUnitStatusType findOne(String id) {
		return eodUnitStatusTypeRepository.findOne(id);
	}
	
	public void save(EodUnitStatusType eodUnitStatusType) {
		eodUnitStatusTypeRepository.save(eodUnitStatusType);
	}
	
	public void delete(String id) {
		eodUnitStatusTypeRepository.delete(id);
	}

}
