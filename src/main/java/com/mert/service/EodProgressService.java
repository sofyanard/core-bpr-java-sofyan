package com.mert.service;

import java.util.Date;
import java.util.List;

import com.mert.model.EodProgress;
import com.mert.repository.EodProgressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EodProgressService {
	
	@Autowired
	private EodProgressRepository eodProgressRepository;
	
	public List<EodProgress> findAll() {
		return eodProgressRepository.findAll();
	}
	
	public EodProgress findOne(String kodeEod) {
		return eodProgressRepository.findOne(kodeEod);
	}
	
	public void save(EodProgress eodProgress) {
		eodProgressRepository.save(eodProgress);
	}
	
	public void delete(String kodeEod) {
		eodProgressRepository.delete(kodeEod);
	}
	
	public void deleteAll() {
		eodProgressRepository.deleteAll();
	}
	
	public void New(String kodeEod) {
		EodProgress eodProgress = new EodProgress();
		eodProgress.setKodeEod(kodeEod);
		eodProgress.setStatus("Not Running");
		eodProgress.setDateStart(null);
		eodProgress.setDateFinish(null);
		eodProgress.setCountStart(0);
		eodProgress.setCountFinish(0);
		eodProgress.setCountNow(0);
		eodProgress.setPercentProgress(0);
		eodProgress.setNote(null);
		eodProgressRepository.save(eodProgress);
	}
	
	public void Start(String kodeEod) {
		EodProgress eodProgress = eodProgressRepository.findOne(kodeEod);
		eodProgress.setStatus("Running");
		eodProgress.setDateStart(new Date());
		eodProgressRepository.save(eodProgress);
	}
	
	public void Finish(String kodeEod) {
		EodProgress eodProgress = eodProgressRepository.findOne(kodeEod);
		eodProgress.setStatus("Finish");
		eodProgress.setDateFinish(new Date());
		eodProgressRepository.save(eodProgress);
	}
	
	public void CountUp(String kodeEod) {
		EodProgress eodProgress = eodProgressRepository.findOne(kodeEod);
		Integer countNow = eodProgress.getCountNow();
		eodProgress.setCountNow(countNow + 1);
		eodProgressRepository.save(eodProgress);
	}
	
	public void SetNote(String kodeEod, String note) {
		EodProgress eodProgress = eodProgressRepository.findOne(kodeEod);
		eodProgress.setNote(note);
		eodProgressRepository.save(eodProgress);
	}

}