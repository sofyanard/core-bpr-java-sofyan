package com.mert.service;

import java.util.List;

import com.mert.model.SkalaAngsuran;
import com.mert.repository.SkalaAngsuranRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SkalaAngsuranService {
	
	@Autowired
	private SkalaAngsuranRepository skalaAngsuranRepository;
	
	public List<SkalaAngsuran> findAll() {
		return skalaAngsuranRepository.findAll();
	}
	
	public SkalaAngsuran findOne(Integer id) {
		return skalaAngsuranRepository.findOne(id);
	}
	
	public void save(SkalaAngsuran skalaAngsuran) {
		skalaAngsuranRepository.save(skalaAngsuran);
	}
	
	public void delete(Integer id) {
		skalaAngsuranRepository.delete(id);
	}
	
	public List<SkalaAngsuran> findByNoRekening(String noRekening) {
		return skalaAngsuranRepository.findByNoRekening(noRekening);
	}
	
	public SkalaAngsuran findByNoRekeningAndBulanKe(String noRekening, Integer bulanKe) {
		return skalaAngsuranRepository.findByNoRekeningAndBulanKe(noRekening, bulanKe);
	}
	
	public SkalaAngsuran findByNoRekeningAndDueDate(String noRekening, String strDate) {
		return skalaAngsuranRepository.findByNoRekeningAndDueDate(noRekening, strDate);
	}
	
	public List<SkalaAngsuran> findByDueDate(String strDate) {
		return skalaAngsuranRepository.findByDueDate(strDate);
	}

}
