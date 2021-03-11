package com.mert.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.RekeningKredit;
import com.mert.repository.RekeningKreditRepository;
import com.mert.model.CurrentRekeningKredit;
import com.mert.repository.CurrentRekeningKreditRepository;

@Service
public class RekeningKreditService {
	
	@Autowired
	private RekeningKreditRepository rekeningKreditRepository;
	
	@Autowired
	private CurrentRekeningKreditRepository currentRekeningKreditRepository;
	
	public List<RekeningKredit> findAll() {
		return rekeningKreditRepository.findAll();
	}
	
	public RekeningKredit findOne(String id) {
		return rekeningKreditRepository.findOne(id);
	}
	
	public void save(RekeningKredit rekeningKredit) {
		rekeningKreditRepository.save(rekeningKredit);
	}
	
	public void delete(String id) {
		rekeningKreditRepository.delete(id);
	}
	
	public RekeningKredit findByNoFasilitas(String nofasilitas) {
		return rekeningKreditRepository.findByNoFasilitas(nofasilitas);
	}
	
	public List<RekeningKredit> searchByProp(String norek, Long nonasabah, String nama) {
		if ((norek != null) && (!norek.trim().isEmpty())) {
			RekeningKredit rekeningKredit = rekeningKreditRepository.findOne(norek);
			List<RekeningKredit> listRekeningKredit = new ArrayList<RekeningKredit>();
			listRekeningKredit.add(rekeningKredit);
			return listRekeningKredit;
		} else if (nonasabah != null) {
			return rekeningKreditRepository.findByNoNasabah(nonasabah);
		} else if ((nama != null) && (!nama.trim().isEmpty())) {
			return rekeningKreditRepository.findByNamaNasabah(nama.toLowerCase());
		} else {
			return new ArrayList<RekeningKredit>();
		}
	}
	
	public String GetNewNoRekening(String prefix) {
		String noRek;
		
		CurrentRekeningKredit currentRekeningKredit = currentRekeningKreditRepository.findByPrefix(prefix);
		
		if (currentRekeningKredit == null) {
			currentRekeningKredit = new CurrentRekeningKredit();
			currentRekeningKredit.setPrefix(prefix);
			currentRekeningKredit.setLastIndex(1);
			currentRekeningKreditRepository.save(currentRekeningKredit);
			
			noRek = prefix + "1" + this.AddZeroLeading(1, 7) + String.valueOf((new Random()).nextInt(10));
		} else {
			Integer nextIndex = currentRekeningKredit.getLastIndex() + 1;
			currentRekeningKredit.setLastIndex(nextIndex);
			currentRekeningKreditRepository.save(currentRekeningKredit);
			
			noRek = prefix + "1" + this.AddZeroLeading(nextIndex, 7) + String.valueOf((new Random()).nextInt(10));
		}
		
		return noRek;
	}
	
	private String AddZeroLeading(Integer input, Integer numdigit) {
		Integer targetLength = numdigit;
		String leadedInput = input.toString();
		
		while (leadedInput.length() < targetLength) {
			leadedInput = "0" + leadedInput;
		}
		
		return leadedInput;
	}
	
	
	
	public List<RekeningKredit> customEodCalculation1011(String unitId) {
		return rekeningKreditRepository.customEodCalculation1011(unitId);
	}
	
	public Integer customEodCalculation1011Count(String unitId) {
		return rekeningKreditRepository.customEodCalculation1011Count(unitId);
	}
	
	public Double customEodCalculation1011SisaAngsuran(String noRek, String strDate) {
		return rekeningKreditRepository.customEodCalculation1011SisaAngsuran(noRek, strDate);
	}
	
}
