package com.mert.service;

import java.util.List;
import java.util.UUID;

import com.mert.model.TranHistory;
import com.mert.repository.TranHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranHistoryService {
	
	@Autowired
	private TranHistoryRepository tranHistoryRepository;
	
	public List<TranHistory> findAll() {
		return tranHistoryRepository.findAll();
	}
	
	public TranHistory findOne(UUID id) {
		return tranHistoryRepository.findOne(id);
	}
	
	public void save(TranHistory tranHistory) {
		tranHistoryRepository.save(tranHistory);
	}
	
	public void delete(UUID id) {
		tranHistoryRepository.delete(id);
	}
	
	public List<TranHistory> findByUnitToday(String unitId) {
		return tranHistoryRepository.findByUnitToday(unitId);
	}
	
	public List<TranHistory> findByUnitAndKoTranToday(String unitId, String koTran) {
		return tranHistoryRepository.findByUnitAndKoTranToday(unitId, koTran);
	}
	
	public List<TranHistory> findByTranRef(UUID tranRef) {
		return tranHistoryRepository.findByTranRef(tranRef);
	}
	
	public List<TranHistory> findCorrectionsByTranRef(UUID tranRef) {
		return tranHistoryRepository.findCorrectionsByTranRef(tranRef);
	}

}
