package com.mert.service;

import java.util.Date;
import java.util.List;

import com.mert.model.EodKalkulasi;
import com.mert.repository.EodKalkulasiRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EodKalkulasiService {
	
	@Autowired
	private EodKalkulasiRepository eodKalkulasiRepository;
	
	@Autowired
	private EodTanggalService eodTanggalService;
	
	public List<EodKalkulasi> findAll() {
		return eodKalkulasiRepository.findAll();
	}
	
	public EodKalkulasi findOne(Long id) {
		return eodKalkulasiRepository.findOne(id);
	}
	
	public void save(EodKalkulasi eodKalkulasi) {
		eodKalkulasiRepository.save(eodKalkulasi);
	}
	
	public void delete(Long id) {
		eodKalkulasiRepository.delete(id);
	}
	
	public void newEntry(
			String kodeEod,
			String calcObject,
			Double calcValue,
			String rekBukuBesar,
			String note) {
		EodKalkulasi eodKalkulasi = new EodKalkulasi();
		eodKalkulasi.setEodTanggal(eodTanggalService.getDate());
		eodKalkulasi.setKodeEod(kodeEod);
		eodKalkulasi.setCalcObject(calcObject);
		eodKalkulasi.setCalcValue(calcValue);
		eodKalkulasi.setRekBukuBesar(rekBukuBesar);
		eodKalkulasi.setCalcDate(new Date());
		eodKalkulasi.setNote(note);
		
		eodKalkulasiRepository.save(eodKalkulasi);
	}

}
