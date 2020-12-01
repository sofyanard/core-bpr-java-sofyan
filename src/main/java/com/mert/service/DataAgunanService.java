package com.mert.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mert.model.DataAgunan;
import com.mert.repository.DataAgunanRepository;
import com.mert.model.CurrentCollateral;
import com.mert.repository.CurrentCollateralRepository;

@Service
public class DataAgunanService {
	
	@Autowired
	private DataAgunanRepository dataAgunanRepository;
	
	@Autowired
	private CurrentCollateralRepository currentCollateralRepository;
	
	public List<DataAgunan> findAll() {
		return dataAgunanRepository.findAll();
	}
	
	public DataAgunan findOne(String id) {
		return dataAgunanRepository.findOne(id);
	}
	
	public void save(DataAgunan dataAgunan) {
		dataAgunanRepository.save(dataAgunan);
	}
	
	public void delete(String id) {
		dataAgunanRepository.delete(id);
	}
	
	public DataAgunan findByNoFasilitas(String nofasilitas) {
		List<DataAgunan> listDataAgunan = dataAgunanRepository.findByNoFasilitas(nofasilitas);
		if (!listDataAgunan.isEmpty()) {
			DataAgunan dataAgunan = listDataAgunan.get(0);
			return dataAgunan;
		} else {
			return new DataAgunan();
		}
	}
	
	public String GetNewCollateralId(Long nonasabah, String jenisagunan) {
		String collateralId = "";
		String strNasabahId = "";
		String strIndex = "";
		
		CurrentCollateral currentCollateral = currentCollateralRepository.findByNasabahIdAndJenisAgunanId(nonasabah, jenisagunan);
		
		if (currentCollateral == null) {
			currentCollateral = new CurrentCollateral();
			currentCollateral.setNasabahId(nonasabah);
			currentCollateral.setJenisAgunanId(jenisagunan);
			currentCollateral.setLastIndex(1);
			currentCollateralRepository.save(currentCollateral);
			
			strNasabahId = nonasabah.toString();
			collateralId = strNasabahId + jenisagunan + this.AddZeroLeading(1);
		} else {
			Integer nextIndex = currentCollateral.getLastIndex() + 1;
			currentCollateral.setLastIndex(nextIndex);
			currentCollateralRepository.save(currentCollateral);
			
			strNasabahId = nonasabah.toString();
			collateralId = strNasabahId + jenisagunan + this.AddZeroLeading(nextIndex);
		}
		
		return collateralId;
	}
	
	private String AddZeroLeading(Integer input) {
		Integer targetLength = 5;
		String leadedInput = input.toString();
		
		while (leadedInput.length() < targetLength) {
			leadedInput = "0" + leadedInput;
		}
		
		return leadedInput;
	}

}
